import java.util.Scanner;

public class AllocateTest {
	//设备列表初始化
	static Equipment []equipment=new Equipment[10];
	static EquipType []equiptype=new EquipType[4];

	static void allocate(String J,String type,int mm){
		int i,t;
		//查询该类设备
		i=0;
		while(i<4) {
			if(equiptype[i].type.equals(type)) {
				if(equiptype[i].remain<1) {//所需设备现存可用台数不足
					System.out.println("该类设备不足,分配失败");
					return;
				}
				t=equiptype[i].address;//取出该类设备在设备表中的起始地址
				while(!(equipment[t].status==1&&equipment[t].whether==0)) {
					t++;
					//填写作业名、相对号，状态改为已分配
					equiptype[i].remain--;
					equipment[t].whether=1;
					equipment[t].jobname=J;
					equipment[t].lnumber=mm;
					System.out.println("设备分配成功");
					return ;
				}
			}
			i++;
		}
		System.out.println("无该类设备,设备分配失败");
		return;
	}
	
	static void reclaim(String J,String type) {
		int i,t,j,k,nn;
		i=0;
		while(i<4) {
			if(equiptype[i].type.equals(type)){
				t=equiptype[i].address;	//取出该类设备在设备表中的起始地址
				j=equiptype[i].count;	//取出该类设备的数量
				k=0;
				nn=t+j;
				for(;t<nn;t++) {
					if(equipment[t].jobname.equals(J)&&equipment[t].whether==1){
						equipment[t].whether=0;
						equipment[t].jobname=" ";
						equipment[t].lnumber=0;
						equiptype[i].remain++;
						k++;
						System.out.println("成功收回该作业占用的第一台该类设备\n");
						return;
					}
					if(k==0) {
						System.out.println("该作业没有使用该类设备\n");
						return;
					}
				}
			}
			i++;
		}
		//没有找到该类设备
		System.out.println("无该类设备，设备回收失败");
		return;
	}

	public static void main(String[] args) {
		int i,mm,a;
		String J=new String();//作业名
		String type=new String();//作业所需设备类
		for(int b=0;b<4;b++) {
			equiptype[b]=new EquipType();
		}
		for(int b=0;b<10;b++) {
			equipment[b]=new Equipment();
		}
		equiptype[0].type=new String("input");//输入机
		equiptype[0].count=2;
		equiptype[0].remain=2;
		equiptype[0].address=0;
		equiptype[1].type=new String("printer");//打印机
		equiptype[1].count=3;
		equiptype[1].remain=3;
		equiptype[1].address=2;
		equiptype[2].type=new String("disk");//磁盘机
		equiptype[2].count=4;
		equiptype[2].remain=4;
		equiptype[2].address=5;
		equiptype[3].type=new String("tape");//磁带机
		equiptype[3].count=1;
		equiptype[3].remain=1;
		equiptype[3].address=9;
		//设备表初始化
		for(i=0;i<10;i++){
			equipment[i].number=i;
			equipment[i].status=1;
			equipment[i].whether=0;
		}
		//System.out.println(equiptype[0].type.equals("input"));
		while(true){
			System.out.println("0－退出,1-分配,2－回收,3－显示");
			System.out.println("选择功能项(0－3):");
			Scanner sc=new Scanner(System.in);
			a=sc.nextInt();
			switch(a)
			{
				case 0://程序结束*/
					return;
				case 1://a=1分配设备
					System.out.println("输入作业名、作业所需设备类和设备相对号");
					//scanf("%s%s%d",J,type,&mm);
					J=sc.next();
					type=sc.next();
					mm=sc.nextInt();
					allocate(J,type,mm);
					break;
				case 2://a=2回收设备
					System.out.println("输入作业名和作业归还的设备类");
					//scanf("%s%s",J,type);
					J=sc.next();
					type=sc.next();
					reclaim( J,type);
					break;
				case 3://a=3输出设备类表和设备表的内容
					System.out.println("输出设备类表");
					System.out.println("设备类型"+"          设备总量"+"\t"+"       空闲好设备\n");
					for(i=0;i<4;i++) {
						System.out.println(equiptype[i].type+"\t\t"+
								equiptype[i].count+"\t\t"+equiptype[i].remain);
					}
						
					System.out.println("输出设备表:\n");
					System.out.println("绝对号 好/坏    已/未分配"+"\t占用作业名      相对号\n");
					for(i=0;i<10;i++)
						/*printf("%3d%8d%9d%12s%8d\n",equipment[i].number,
						equipment[i].status,equipment[i].remain,equipment[i].jobname,
						equipment[i].lnumber);*/
						System.out.println(equipment[i].number+"\t"+equipment[i].status
									+"\t"+equipment[i].whether+"\t"+equipment[i].jobname
									+"\t\t"+equipment[i].lnumber);
			}
		}
	}
}
