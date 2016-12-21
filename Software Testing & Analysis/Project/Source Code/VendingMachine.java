public class VendingMachine
{
	private int x;
	private int price;
	private int k;
	private int k1;
	private int t;
	private int s;

	public VendingMachine()
	{
		k1 = 0;
		k = 0;
		t = 0;
		price = 0;
		x = 1;
	}
	public final int coin()
	{
		if (x == 1)
		{
			if ((t + 25 >= price) && (price > 0))
			{
				s = 0;
				t = 0;
				x = 2;
				return 1;
			}
			else if (t + 25 < price)
			{
				t = t + 25;
				return 1;
			}
		}
		else if ((x > 1) && (x < 6))
		{
			System.out.print("RETURN COIN");
			System.out.print("\n");
			return 1;
		}
		return 0;
	}
	public final int small_cup()
	{
		if ((x == 2) || (x == 3))
		{
			s = 2;
			return 1;
		}
		return 0;
	}
	public final int large_cup()
	{
		if ((x == 2) || (x == 3))
		{
			s = 1;
			return 1;
		}
		return 0;
	}
	public final int sugar()
	{
		if ((x == 2) || (x == 3))
		{
			if (x == 2)
			{
				x = 3;
			}
			else
			{
				x = 2;
			}
			return 1;
		}
		return 0;
	}
	public final int tea()
	{
		if ((x == 2) || (x == 3))
		{
			if ((x == 2) && (k1 > 1) && (s == 2))
			{
				System.out.print("DISPOSE SMALL CUP OF TEA");
				System.out.print("\n");
				k1 = k1 - 1;
				x = 1;
				return 1;
			}
			else if ((x == 2) && (k > 1) && (s == 1))
			{
				System.out.print("DISPOSE LARGE CUP OF TEA");
				System.out.print("\n");
				k = k - 1;
				x = 1;
				return 1;
			}
			else if ((x == 2) && (k == 1) && (s == 1))
			{
				System.out.print("DISPOSE LARGE CUP OF TEA");
				System.out.print("\n");
				k = k - 1;
				x = 5;
				return 1;
			}
			else if ((x == 2) && (k1 == 1) && (s == 2))
			{
				System.out.print("DISPOSE SMALL CUP OF TEA");
				System.out.print("\n");
				k1 = k1 - 1;
				x = 4;
				return 1;
			}
			else if ((x == 3) && (k1 == 1) && (s == 2))
			{
				System.out.print("DISPOSE SMALL CUP OF TEA WITH SUGAR");
				System.out.print("\n");
				k1 = k1 - 1;
				x = 4;
				return 1;
			}
			else if ((x == 3) && (k == 1) && (s == 1))
			{
				System.out.print("DISPOSE LARGE CUP OF TEA WITH SUGAR");
				System.out.print("\n");
				k = k - 1;
				x = 5;
				return 1;
			}
			if ((x == 3) && (k1 > 1) && (s == 2))
			{
				System.out.print("DISPOSE SMALL CUP OF TEA WITH SUGAR");
				System.out.print("\n");
				k1 = k1 - 1;
				x = 1;
				return 1;
			}
			else if ((x == 3) && (k > 1) && (s == 1))
			{
				System.out.print("DISPOSE LARGE CUP OF TEA WITH SUGAR");
				System.out.print("\n");
				k = k - 1;
				x = 1;
				return 1;
			}
			return 0;
		}
		return 0;
	}
	public final int insert_large_cups(int n)
	{
		if ((x == 1) && (n > 0))
		{
			k = k + n;
			return 1;
		}
		else if ((x == 5) && (n > 0))
		{
			k = n;
			x = 1;
			return 1;
		}
		return 0;
	}
	public final int insert_small_cups(int n)
	{
		if ((x == 1) && (n > 0))
		{
			k1 = k1 + n;
			return 1;
		}
		else if ((x == 4) && (n > 0))
		{
			k1 = n;
			x = 1;
			return 1;
		}
		return 0;
	}
	public final int set_price(int p)
	{
		if ((x == 1) && (p > 0))
		{
			price = p;
			return 1;
		}
		return 0;
	}
	public final int cancel()
	{
		if ((x == 2) || (x == 3))
		{
			System.out.print("RETURN COINS");
			System.out.print("\n");
			x = 1;
			return 1;
		}
		return 0;
	}
	public final int dispose()
	{
		if ((x == 1))
		{
			System.out.print("SHUT DOWN");
			System.out.print("\n");
			x = 6;
			return 1;
		}
		return 0;
	}
	
	void Show_State(){
		System.out.println("");
		if(x==1){
			System.out.println("State : Idle");
		}else if(x==2){
			System.out.println("State : Coin Inserted");
		}else if(x==3){
			System.out.println("State : Sugar");
		}else if(x==4){
			System.out.println("State : no_Large_Cups");
		}else if(x==5){
			System.out.println("State : no_Small_Cups");
		}
		System.out.println("");
	}
	
	void Show_All_Values(){
		System.out.println("Price="+price);
		System.out.println("No_of_Large_Cups="+k);
		System.out.println("No_of_small_Cups="+k1);
		System.out.println("T="+t);
		System.out.println("CurrentState="+x);
	}
}