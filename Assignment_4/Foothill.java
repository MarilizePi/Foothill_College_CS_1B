public class Foothill {

	public static void main(String[] args) {

		// Test 1

		InternetUser user, user1, user2, user3;
		System.out.println("/*---------------------------------------------------------");
		System.out.println("Base Class Testing ***********************\n");

		user = new InternetUser();
		user1 = new InternetUser("dns", "");
		user2 = new InternetUser("n", "75.75.75.75");
		user3 = new InternetUser();
		user3.setName("bad");

		System.out.println(user);
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		System.out.println("\nsuccess as expected");
		System.out.println("---------------------------------------------------------*/");

		// Test Communicator 2

		Communicator com1, com2, com3, com4, com5;
		com1 = new Communicator();
		com2 = new Communicator("salim lakhani", "10.1.10.1");
		com3 = new Communicator("yan kam", "127.90.32.14");
		com4 = new Communicator();
		com4.setName("sally");
		com5 = new Communicator("betty", "1.1.1.1");

		System.out.println("\n" + "Derived Class Constructor Testing ***************");
		System.out.println("------------" + "\n" + "Name: " + com1.getName() + "\n" + "IP Addr: " + com1.getIp()
				+ "\n\n" + com1 + "\n");
		System.out.println("------------" + "\n" + "Name: " + com2.getName() + "\n" + "IP Addr: " + com2.getIp()
				+ "\n\n" + com2 + "\n");
		System.out.println("------------" + "\n" + "Name: " + com3.getName() + "\n" + "IP Addr: " + com3.getIp()
				+ "\n\n" + com3 + "\n");
		System.out.println("------------" + "\n" + "Name: " + com4.getName() + "\n" + "IP Addr: " + com4.getIp()
				+ "\n\n" + com4 + "\n");
		System.out.println("------------" + "\n" + "Name: " + com5.getName() + "\n" + "IP Addr: " + com5.getIp()
				+ "\n\n" + com5 + "\n");

		System.out.println("\nDerived and Base Class Mutator Testing **********");
		Communicator com6;
		com6 = new Communicator();
		com6.setName("giral");
		com6.setPrimes(809, 821);

		System.out.println("------------" + "\n" + "Name: " + com6.getName() + "\n" + "IP Addr: " + com6.getIp()
				+ "\n\n" + com6 + "\n");

		System.out.println("\n\n***************Option B1***************");

		// create an IuStack. Then push() various InternetUsers onto the stack
		IuStack iustk = new IuStack();
		InternetUser iu, current;
		iu = new InternetUser("Mari", "1.1.1.1");
		iustk.pushIu(iu);
		iu = new InternetUser("Brian", "2.2.2.2");
		iustk.pushIu(iu);
		iu = new InternetUser("John", "3.3.3.3");
		iustk.pushIu(iu);
		iu = new InternetUser("Melissa", "4.4.4.4");
		iustk.pushIu(iu);
		iu = new InternetUser("Hugo", "5.5.5.5");
		iustk.pushIu(iu);
		/*
		 * Finally, in a loop, pop() everything off the IuStack and print it out as you
		 * pop(). Go beyond the end of the Stack so you can confirm that your code does
		 * not break when you pop() things off an empty Stack.
		 */
		// 3 more times test for empty stack
		for (int i = 0; i < 8; i++) {
			if ((current = iustk.popIu()) != IuStack.STACK_EMPTY)
				System.out.println(current + "\n");
		}

		// for (int i=0; i<5; i++ ) {
		// if ( ( iustk.popIu()) != IuStack.STACK_EMPTY)
		// System.out.println(iustk.popIu());
		// }
	}

}