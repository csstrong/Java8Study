package com.chensi.designpattern.dp1;

/*
 * @author  chensi
 * @date  2023/4/7
 */
//Decorator类
abstract class Decorator extends Component {

	protected Component component;

	//装饰一个Component对象
	public void SetComponent(Component component) {
		this.component = component;
	}

	//重写Operation(),实际调用component的Operation方法
	@Override
	public void Operation() {
		if (component != null) {
			component.Operation();
		}
	}
}
