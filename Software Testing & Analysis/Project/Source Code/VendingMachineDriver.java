import java.io.*;
import java.util.Scanner;


public class VendingMachineDriver
{
   public static void main(String [] args)
   {

	   int ch=0;
	  int r=-1;
	  int n;
	 
	  VendingMachine obj=new VendingMachine();
	  Scanner in = new Scanner(System.in);
	  
	 
	 while(ch!=11){
		 
		 
		 System.out.println("1.Set_Price");
		 System.out.println("2.Insert_Large_Cups");
		 System.out.println("3.Insert_Small_Cups");
		 System.out.println("4.Coin");
		 System.out.println("5.Small_Cup");
		 System.out.println("6.Large_Cup");
		 System.out.println("7.Sugar");
		 System.out.println("8.Tea");
		 System.out.println("9.Cancel");
		 System.out.println("10.Dispose");
		 System.out.println("11.Quit");
		 System.out.println("12.Show_state");
		 System.out.println("13.Show_All_Values");
		 System.out.println("");
		 System.out.println("Enter your choice:");
		 ch=in.nextInt();
		 
		 switch(ch){
		 case 1:
			 int price;
			 System.out.println("Set_Price(int p)");
			 System.out.println("Enter value of p");
			 price=in.nextInt();
			 
			 r=obj.set_price(price);
			 System.out.println("The value returned is ="+ r);
			 break;
		 case 2:
			 System.out.println("Insert_Large_Cups(int n)");
			 System.out.println("Enter value of n");
			 n=in.nextInt();
			 
			 r=obj.insert_large_cups(n);
			 System.out.println("The value returned is="+r);
			 break;
		 case 3:
			 System.out.println("Insert_Small_Cups(int n)");
			 System.out.println("Enter value of n");
			 n=in.nextInt();
			 
			 r=obj.insert_small_cups(n);
			 System.out.println("The value returned is="+r);
			 break;
		 case 4:
			 System.out.println("Coin");
			 r=obj.coin();
			 System.out.println("The value returned is="+r);
			 break;
		 case 5:
			 System.out.println("Small_Cup");
			 r=obj.small_cup();
			 System.out.println("The value returned is="+r);
			 break;
		 case 6:
			 System.out.println("Large_Cup");
			 r=obj.large_cup();
			 System.out.println("The value returned is="+r);
			 break;
		 case 7:
			 System.out.println("Sugar");
			 r=obj.sugar();
			 System.out.println("The value returned is="+r);
			 break;
		 case 8:
			 System.out.println("Tea");
			 r=obj.tea();
			 System.out.println("The value returned is="+r);
			 break;
		 case 9:
			 System.out.println("Cancel");
			 r=obj.cancel();
			 System.out.println("The value returned is="+r);
			 break;
		 case 10:
			 System.out.println("Dispose");
			 r=obj.dispose();
			 System.out.println("The value returned is="+r);
			 break;
		 case 12:
			 System.out.println("Show_State");
			 obj.Show_State();
			 //System.out.println("The value returned is="+r);
			 break;
		 case 13:
			 System.out.println("Show_All_Values");
			 obj.Show_All_Values();
			 //System.out.println("The value returned is="+r);
			 break;
		 default:
			 if(ch!=11){
			 System.out.println("Please choose only from the given options!!");
			 System.out.println("Else Enter 11 to quit");
			 }
				 break;
		 }
		 System.out.println("");
		 System.out.println("");
						 
	 }
	 
	 System.out.println("VendingMachine Driver is Stopped");
	 System.exit(0);
   }
}