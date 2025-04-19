package edu.neu.csye7374;


public class Demo {
    public static void builderDemo(){
        Address address = new Address.Builder()
                .setStreet("20 S Huntington Ave")
                .setCity("Boston")
                .setState("MA")
                .setZipcode("02130")
                .build();
        System.out.println(address.getStreet());
        System.out.println(address.getCity());
        System.out.println(address.getState());
        System.out.println(address.getZipcode());
    }

    public static void bridgeDemo(){
        System.out.println();
        System.out.println("Shreyas Demo:");
        System.out.println("*************");
        APerson aPerson = new APerson(new Person(25, "Shreyas"));
        aPerson.talk();

        System.out.println();
        System.out.println("Yakgna Demo:");
        System.out.println("*************");
        BPerson bPerson = new BPerson(new Person(28, "Yakgna"));
        bPerson.talk();
        bPerson.walk();
        bPerson.run();
        bPerson.jump();

        System.out.println();
        System.out.println("Ajith Demo:");
        System.out.println("*************");
        CPerson cPerson = new CPerson(new Employee(34, "Ajith", "SDE", 44.76));
        cPerson.talk();
        cPerson.walk();
        cPerson.run();
        cPerson.jump();
    }



}

