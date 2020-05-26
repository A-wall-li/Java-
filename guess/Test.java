package guess;

import java.util.Scanner;

/**
 * 
 * @author LITIANYI
 * @version 0.0.1
 * @since 创建时间 2020年1月7日上午8:52:29
 * @Description:双色球彩票系统(测试版):程序主入口
 * @package guess
 */
public class Test {
	static int list;// 声明一个全局变量

	public static void main(String[] args) {
		boolean flan = false;// 退出凭证
		boolean flam = false;// 记录是否投注
		Way way = new Way();
		Scanner sc = new Scanner(System.in);
		System.out.println("************新年快乐**************");
		System.out.println("    欢迎光临双色球彩票系统(测试版)");
		System.out.println("\t1.我要投注");
		System.out.println("\t2.查看开奖");
		System.out.println("\t3.充值会员");
		System.out.println("\t4.了解规则");
		System.out.println("\t0.退出系统");
		System.out.println("注:建议使用本系统之前,点击了解规则,若使用本系统,则默认您已知晓并遵守本规则");
		System.out.println("************新年快乐**************");
		while (flan == false) {
			System.out.println("请输入以上功能的序列号");
			list = sc.nextInt();
			switch (list) {
			case 0:
				flan = true;// 退出功能
				break;
			case 1:
				way.bet();
				flam = true;// 在查看开奖前必须点击了我要投注功能
				break;
			case 2:
				way.judge(flam);
				break;
			case 3:
				way.pay();
				break;
			case 4:
				way.statement();
				break;
			default:
				System.out.println("输入错误,请重新输入");
				break;
			}
		}
		System.out.println("退出成功,欢迎下次使用");
		System.out.println("鼠年将到，福鼠祝你鸿福齐天，万事如意；祥鼠祝你财气冲天，好运无穷；吉鼠祝你吉星高照，心想事成;");
		System.out.println("灵鼠祝你天天开心，福寿安康；朋友则祝你鼠年如意，万事顺心~！");
	}
}
