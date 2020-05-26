package guess;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author LITIANYI
 * @version 0.0.1
 * @since 创建时间 2020年1月7日上午8:53:28
 * @Description:双色球系统(测试版)管理类:双色球彩票系统的投注,中奖,输出中奖,使用规则
 * @package guess
 */
public class Way {
	Scanner sc = new Scanner(System.in);
	Lottery lottery = new Lottery();
	int prize = 0;// 记录中奖等级
	int vip;// 记录vip等级(1-6)输出红球
	int vvip;// vip7级输出篮球
	int v;// 记录累计充值金额
	boolean foan = false;
	int svip;
//	boolean fian = false;

	// 投注;
	void bet() {
		boolean ftan = true;// 人工投注中篮球的循环条件
		boolean flan = true;// 外层do-while循环条件
		do {
			System.out.println("请选择自动投注,或人工投注");
			String str = sc.next();
			if (str.equals("自动投注")) {
				System.out.println("请输入你要投注的数量");
				lottery.quantity = sc.nextInt();
				if (lottery.quantity >= 100) {
					System.out.println("鉴于您的注资超过200元,温馨提示:");
					System.out.println("小赌怡情，大赌败家，远离赌博，家和业兴。");
				}
				for (int i = 0; i < 6; i++) {
					int n = 1 + new Random().nextInt(33);
					lottery.redDall[i] = n;
				}
				lottery.basketball = 1 + new Random().nextInt(16);
				System.out.println("\n自动投注的红球号码是" + Arrays.toString(lottery.redDall));
				System.out.println("自动投注的蓝球号码为" + lottery.basketball);
				flan = true;
			} else if (str.equals("人工投注")) {
				if (vip != 0 || vvip == 7) {
					privilege();// 判断是否有会员身份
				}
				System.out.println("请输入你要投注的数量");
				lottery.quantity = sc.nextInt();
				if (lottery.quantity >= 100) {
					System.out.println("鉴于您的注资超过200元,温馨提示:");
					System.out.println("小赌伤情，大赌伤家，远离赌博，家和业兴。");
				}
				int i = 0;
				System.out.println("请输入你的红色球号码");
				do {
					System.out.println("输入6个红色球号<数字为1-33>，第" + (i + 1) + "个红色球号为：");
					int red = sc.nextInt();
					if (red > 0 && red < 34) {
						for (int j = 0; j < lottery.redDall.length; j++) {
							if (lottery.redDall[i] == red) {
								System.out.println("输入了重复的数字，请再次输入第" + (i + 1) + "个值：");
							}
						}
						lottery.redDall[i] = red;
						i++;
					} else {
						System.out.println("输入了超出范围的数字，请再次输入第" + (i + 1) + "个值：");
					}
				} while (i < 6);
				do {
					System.out.println("请输入你的篮球号码<数字为1-16>,如果输入错误,请重新输入");
					int j = sc.nextInt();
					if (j < 17 && j > 0) {
						lottery.basketball = j;
						ftan = false;
					}
				} while (ftan == true);
				flan = true;
				System.out.println("\n你输入的红球号码为" + Arrays.toString(lottery.redDall));
				System.out.println("你输入的蓝球号码为" + lottery.basketball);

			} else {
				System.out.println("对不起,输入错误");
				flan = false;
			}
			flan = false;
		} while (flan == true);
		System.out.println("\n您的投注数量为" + lottery.quantity + "金额为" + (lottery.quantity * 2));
	}

	// 充值会员
	void pay() {
		System.out.println("请输入您要充值的金额");
		int s = sc.nextInt();
		v += s;
		if (v >= 1 && v < 2) {
			vip = 1;
			System.out.println("您已是vip" + vip + "会员,中奖概率增加");
		} else if (v > 1 && v <= 10) {
			vip = 2;
			System.out.println("您已是vip" + vip + "会员,中奖概率增加");
		} else if (v >= 11 && v <= 50) {
			vip = 3;
			System.out.println("您已是vip" + vip + "会员,中奖概率增加");
		} else if (v >= 51 && v <= 200) {
			vip = 4;
			System.out.println("您已是vip" + vip + "会员");
		} else if (v >= 201 && v <= 500) {
			vip = 5;
			System.out.println("您已是vip" + vip + "会员");
		} else if (v >= 501 && v <= 1000) {
			vip = 6;
			System.out.println("您已是vip" + vip + "会员");
		} else if (v > 1000) {
			foan = true;
			svip = 6;
			vvip = 7;
			System.out.println("您已是vip" + vvip + "会员");
		} else {
			System.out.println("充值错误,抱歉");
		}

	}

	// 会员特权
	void privilege() {
		lottery.winningNumbers();
		if (foan == false) {
			System.out.println("尊敬的vip" + vip + "您好,您可以看到" + vip + "个中奖号码");
		} else {
			System.out.println("尊敬的vip" + vvip + "您好,您可以看到" + vvip + "个中奖号码");

		}
		for (int i = 0; i < vip; i++) {
			System.out.print(lottery.redDalls[i] + "  ");
		}
		for (int i = 0; i < svip; i++) {
			System.out.print(lottery.redDalls[i] + "  ");
		}
		if (vvip == 7) {
			System.out.print(lottery.basketballs);
		}
	}

	// 中奖
	int winning() {
		int[] arr1 = new int[lottery.redDalls.length];// 随机生成的中奖码
		int[] arr2 = new int[lottery.redDall.length];// 自己输入的号码
		for (int i = 0; i <= arr1.length - 1; i++) {
			arr1[i] = lottery.redDalls[i];
		}
		for (int i = 0; i <= arr2.length - 1; i++) {
			arr2[i] = lottery.redDall[i];
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		int leading = 0;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr1.length; j++) {
				if (arr2[i] == arr1[j]) {// 输入的值对比系统生成的值,若判断成立,则进入
					leading++;// 对比,一个值相等,就加一
					break;
				}
			}
		}
		boolean flas = (lottery.basketballs == lottery.basketball);// 判断蓝球是否相等
		if (leading == 6 && flas) {// 判断中奖等级
			prize = 1;
		} else if (leading == 6) {
			prize = 2;
		} else if (leading == 5 && flas) {
			prize = 3;
		} else if (leading == 5 || (leading == 4 && flas)) {
			prize = 4;
		} else if (leading == 4 || (leading == 3 && flas)) {
			prize = 5;
		} else if (flas) {
			prize = 6;
		} else {
			prize = 0;
		}
		return prize;

	}

	// 中奖信息
	void judge(boolean flam) {
		winning();
		if (flam == true) {
			switch (winning()) {
			case 1:
				System.out.println("\n恭喜您，中了一等奖，共下" + lottery.quantity + "注，投资" + (lottery.quantity * 2) + "元");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
				break;
			case 2:
				System.out.println("\n恭喜您，中了二等奖，共下" + lottery.quantity + "注，投资" + (lottery.quantity * 2) + "元");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
				break;
			case 3:
				System.out.println("\n恭喜您，中了三等奖，共下" + lottery.quantity + "注，投资" + (lottery.quantity * 2) + "元");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
				break;
			case 4:
				System.out.println("\n恭喜您，中了四等奖，共下" + lottery.quantity + "注，投资" + (lottery.quantity * 2) + "元");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
				break;
			case 5:
				System.out.println("\n恭喜您，中了五等奖，共下" + lottery.quantity + "注，投资" + (lottery.quantity * 2) + "元");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
				break;
			case 6:
				System.out.println("\n恭喜您，中六了等奖，共下" + lottery.quantity + "注，投资" + (lottery.quantity * 2) + "元");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
				break;
			default:
				System.out.println("\n十分抱歉，您没中奖");
				System.out.println("本期中奖号码为" + lottery.winningNumbers());
			}
		} else {
			System.out.println("请先投注");

		}
	}

	// 使用规则
	void statement() {
		System.out.println(lottery.winningNumbers());
		System.out.println("双色球系统使用规则");
		System.out.println("第一条");
		System.out.println("双色球投注区分为红色球号码区和蓝色球号码区，" + "红色球号码区由1-33共三十三个号码组成，");
		System.out.println("蓝色球号码区由1-16共十六个号码组成。" + "投注时选择6个红色球号码和1个蓝色球号码组成一注进行单式投注，每注金额人民币2元。");
		System.out.println("第二条");
		System.out.println("购买者可选择机选号码投注、自选号码投注。" + "机选号码投注是指由投注机随机产生投注号码进行投注，");
		System.out.println("自选号码投注是指将购买者选定的号码输入销售终端进行投注。");
		System.out.println("充值规则");
		System.out.println("用户可以选择充值金额,充值的钱仅作提前了解中奖号码使用");
		System.out.println("充值金额为一元时,为vip1会员.1元以上,10元以内时,为vip2会员.10元以上,50元以内时,为vip3会员.50元以上,200元以内时,为vip4会员.");
		System.out.println("200元以上,500元以内时,为vip5会员.500元以上,1000元以内时,为vip6会员.1000元以上,为vip7会员.");
		System.out.println("一等奖：投注号码与当期开奖号码全部相同（顺序不限，下同），即中奖；");
		System.out.println("二等奖：投注号码与当期开奖号码中的6个红色球号码相同，即中奖；");
		System.out.println("三等奖：投注号码与当期开奖号码中的任意5个红色球号码和1个蓝色球号码相同，即中奖；");
		System.out.println("四等奖：投注号码与当期开奖号码中的任意5个红色球号码相同，或与任意4个红色球号码和1个蓝色球号码相同，即中奖；");
		System.out.println("五等奖：投注号码与当期开奖号码中的任意4个红色球号码相同，或与任意3个红色球号码和1个蓝色球号码相同，即中奖；");
		System.out.println("六等奖：投注号码与当期开奖号码中的1个蓝色球号码相同，即中奖。");
	}
}
