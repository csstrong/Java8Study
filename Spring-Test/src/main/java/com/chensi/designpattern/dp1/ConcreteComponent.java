package com.chensi.designpattern.dp1;

/*
 * @author  chensi
 * @date  2023/4/7
 */
//ConcreteComponent类
public class ConcreteComponent extends Component {

	@Override
	public void Operation() {
		System.out.println("具体对象的实际操作");
	}
}
