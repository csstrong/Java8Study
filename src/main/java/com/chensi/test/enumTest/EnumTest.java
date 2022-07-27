package com.chensi.test.enumTest;


/***********************************
 * @author chensi
 * @date 2022/2/22 15:03
 ***********************************/
public class EnumTest {

    public static void main(String[] args) {
        forEnum();
        useEnumInJava();
    }

    /**
     * 季节枚举
     * Ordinal属性，对应的就是排列顺序，从0开始
     */
    private enum SimpleEnum {
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER
    }

    /**
     * 常用类型，带参数的枚举常量。
     */
    private enum TYPE {
        FIREWALL("firewall"),
        SECRET("secretMac"),
        BALANCE("f5");

        private String typeName;

        TYPE(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return this.typeName;
        }

        /**
         * 根据类型的名称，返回类型的枚举类型
         */
        public static TYPE fromTypeName(String typeName) {
            for (TYPE type : TYPE.values()) {
                if (type.getTypeName().equals(typeName)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 循环枚举，输出ordinal属性；若枚举有内部属性，则也输出。
     */
    private static void forEnum() {
        for (SimpleEnum simpleEnum : SimpleEnum.values()) {
            System.out.println(simpleEnum + " ordinal " + simpleEnum.ordinal());
        }
        System.out.println("---------");
        for (TYPE type : TYPE.values()) {
            System.out.println("type= " + type + " type.name= " + type.name() + " typeName= " + type.getTypeName() + " ordinal= " + type.ordinal());
        }
    }

    /**
     * 在java代码中使用枚举
     */
    private static void useEnumInJava(){
        String typeName="f5";
        TYPE type=TYPE.fromTypeName(typeName);
        System.out.println(type);
        if(TYPE.BALANCE.equals(type)){
            System.out.println("根据字符串获得的枚举类型实例跟枚举常量一致");
        }else {
            System.out.println("代码错误。");
        }
    }


}
