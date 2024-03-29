package com.chensi.tree;

import java.util.Comparator;

/*
 * @author  chensi
 * @date  2022/12/14
 */
class NodeIDComparator implements Comparator {

	// 按照节点编号比较
	public int compare(Object o1, Object o2) {
		int j1 = Integer.parseInt(((Node) o1).id);
		int j2 = Integer.parseInt(((Node) o2).id);
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
	}
}

