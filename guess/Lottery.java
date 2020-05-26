package guess;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author LITIANYI
 * @version 0.0.1
 * @since 创建时间 2020年1月7日下午1:50:53
 * @Description:彩票类:把彩票当作一个对象,它的属性有:单价,投注量, 接收的红球.篮球, 随机生成的红球.篮球,
 *                                          它的行为:随机生成一组红球号码,和一个篮球号码,作为它的中奖号码,
 * @package guess
 */
class Lottery {
//	String name;// 用户名
	int univalence;// 一注的价格
	int quantity;// 投多少注
	int[] redDalls = new int[6];// 红球数组
	int[] redDall = new int[6];// 接收的红球数组
	int basketball;// 接收的蓝球
	int basketballs;// 蓝球

	// 中奖号码
	String winningNumbers() {
		for (int i = 0; i < redDalls.length; i++) {
			int red = 1 + new Random().nextInt(33);
			if (red != redDalls[i]) {
				redDalls[i] = red;
			}
		}
		basketballs = 1 + new Random().nextInt(16);
		return "红球号码为:" + Arrays.toString(redDalls) + "蓝球号码为:" + basketballs;
	}
}
