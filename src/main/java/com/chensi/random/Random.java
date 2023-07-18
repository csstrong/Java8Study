package com.chensi.random;

/**
 * @author chensi
 * @date 2023/7/17
 */
public class Random {
	public static void main(String[] args) {
		double bytP = 0.01;
		double sngBegin = 1;
		double sngEnd = 5;
		double sngPB = 2;
		double sngPE = 3;

		for (int i = 0; i < 100; i++) {
			double d = getRandomNumP(sngBegin, sngEnd, sngPB, sngPE, bytP);
			System.out.println(d);
		}
	}

	/**
	 * @param pSngBegin 随机数范围内的开始数字
	 * @param pSngEnd   随机数范围结束数字
	 * @param pSngPB    要随机的数字的开始数字
	 * @param pSngPE    要随机的数字的结束数字
	 * @param pBytP     要随机的数字的随机概率
	 * @return
	 */
	public static double getRandomNumP(double pSngBegin, double pSngEnd, double pSngPB, double pSngPE, double pBytP) {

		double sngPLen;
		//totla length
		double sngTLen;
		//需要缩放的长度
		double sngIncreased;
		double sngResult;

		sngPLen = pSngPE - pSngPB;
		sngTLen = pSngEnd - pSngBegin;

		if ((sngPLen / sngTLen) * 100 == pBytP) {
			return getRandomNum(pSngBegin, pSngEnd);
		} else {
			//缩放回原来的区间
			sngIncreased = ((pBytP / 100) * sngTLen-sngPLen) / (1 - (pBytP / 100));
			sngResult = getRandomNum(pSngBegin, pSngBegin + sngIncreased);
			if (pSngBegin <= sngResult && sngResult <= pSngPB) {
				return sngResult;
			} else if (pSngPB <= sngResult && sngResult <= (pSngPE + sngIncreased)) {
				return pSngPB + (sngResult - pSngPB) * sngPLen / (sngPLen + sngIncreased);
			} else if ((pSngPE + sngIncreased) <= sngResult && sngResult <= (pSngEnd + sngIncreased)) {
				return sngResult - sngIncreased;
			}
		}
		return 0d;
	}

	public static double getRandomNum(double pSngBegin, double pSngEnd) {
		return (pSngEnd - pSngBegin) * Math.random() + pSngBegin;
	}
}
