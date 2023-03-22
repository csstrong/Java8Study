var fs = require('fs');
var path = require('path');
var JSZip = require('jszip');
var config = {
  // 文件根目录
  dir: "src/services/cloud/drainageHouse/temp/"
}

/**
 * 把mtl文件和obj文件打包成zip压缩包
 * @param  {} fileName 不带文件后缀的文件名
 * @param  {} {delSource = false } = {} 是否删除源文件
 */
function toZipOfMtlObj (fileName, { delSource = false } = {}) {
  var zip = new JSZip();
  var extArr = ['.doc', '.docx'];

  extArr.forEach(ext => {
    let file = fileName + ext;
    let content = getFileContent(fileName + ext);
    zip.file(file, content);
  })

  // 压缩
  zip.generateAsync({
    // 压缩类型选择nodebuffer，在回调函数中会返回zip压缩包的Buffer的值，再利用fs保存至本地
    type: "nodebuffer",
    // 压缩算法
    compression: "DEFLATE",
    compressionOptions: {
      level: 9
    }
  }).then(function (content) {
    let zip = fileName + '.zip';
    // 写入磁盘
    fs.writeFile(getFullFileName(zip), content, function (err) {
      if (!err) {
        // 是否删除源文件
        if (delSource) {
          extArr.forEach(ext => {
            delFile(fileName + ext);
          })
        }
      } else {
        console.log(zip + '压缩失败');
      }
    });
  });
}

function getFileContent (fileName) {
  // 指定encoding会返回一个string，否则返回一个Buffer
  let content = fs.readFileSync(getFullFileName(fileName), { encoding: "utf-8" });
  return content;
}

function getFullFileName (fileName) {
  return path.join(config.dir, fileName);
}

function delFile (fileName) {
  fs.unlink(getFullFileName(fileName), function (err) {
    if (!!err) {
      console.log('删除文件失败：' + file);
    }
  });
}

function fileDisplay (filePath) {
  let filePathList = [];
  //根据文件路径读取文件，返回文件列表
  fs.readdir(filePath, function (err, files) {
    if (err) {
      console.warn(err)
    } else {
      files.forEach(function (filename) {
        var filedir = path.join(filePath, filename);
        fs.stat(filedir, function (eror, stats) {
          if (eror) {
            console.warn('获取文件stats失败');
          } else {
            var isFile = stats.isFile();
            var isDir = stats.isDirectory();
            if (isFile) {
              console.log(filedir);
              filePathList.push(filedir);
            }
            if (isDir) {
              fileDisplay(filedir);
            }
          }
        })
      });
    }
  });
}

function zipFile (bufferJson, path, fileName) {
  var zip = new JSZip();

  for (let name in bufferJson) {
    zip.file(name, bufferJson[name]);
  }
  // 压缩
  zip.generateAsync({
    // 压缩类型选择nodebuffer，在回调函数中会返回zip压缩包的Buffer的值，再利用fs保存至本地
    type: "nodebuffer",
    // 压缩算法
    compression: "DEFLATE",
    compressionOptions: {
      level: 9
    }
  }).then(function (content) {
    let zipFileName = fileName + '.zip';
    // 写入磁盘
    fs.writeFile(path + '/' + zipFileName, content, function (err) {
      if (!err) {
        console.log(zipFileName + '压缩成功');
      } else {
        console.log(zipFileName + '压缩失败');
      }
    });
  });
}

exports.fileDisplay = fileDisplay;
exports.toZipOfMtlObj = toZipOfMtlObj;
exports.zipFile = zipFile;