package regular;

import java.util.Scanner;

import org.springframework.context.annotation.ScannedGenericBeanDefinition;

public class regularexp 
{

	public static void main(String[] args)
	{
		String pattern ="";
		Scanner sc = new Scanner(System.in);
		String b = sc.nextLine();		
		System.out.println("Enter the Value");
		if(b.matches(pattern))
		{
			System.out.println("Yes ");
			
		}
		else
		{
			System.out.println("no ");
		}
	
	
	}


}
