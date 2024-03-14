import java.util.Scanner;
import java.util.Random;

public class OuLGame {
    Scanner player = new Scanner(System.in);
    Random r = new Random();
    String answer;

    String name;
    double hp, stamina, mana;
    int gold;
    double maxHp, maxStam, maxMana;
    String rank;
    String s1, s2, m1, m2;
    double opphp;
    int dmg;
    int oppDmg;
    int lDmg, nDmg;
    int mStat, bStat;
    double newDmg;
    int cooldown, cooldownM, cooldownN, cooldownB;
    boolean magicFlag = false;
    boolean farmerFlag = false;
    int shoppeFlag = 0;
    int troopFlag = 0;
    int day = 0;
    boolean battleFlag = false;
    boolean oppFlag = false;
    boolean nightFlag = false;
    boolean mightFlag = false;
    boolean blightFlag = false;
    int n, p = 0;
    double shield = 0;
    int oppCount = 0;
    int restore = 1;

    public static void main(String[] args) {
        OuLGame game;
        game = new OuLGame();

        game.storyInit();
        game.storyBegin();
    }

    public void storyInit() {
        System.out.println("Welcome to A Knight's Quest, enter anything to start!");
        player.nextLine();
        name = "Taiwyc";
        rank = "Knight";
        maxHp = 100;
        maxStam = 25;
        maxMana = 10;
        dmg = 15;
        gold = 100;
        s1 = "Fast Swing";
    }

    public void storyBegin() {
        hp = maxHp;
        stamina = maxStam;
        mana = maxMana;
        opphp = 50;
        oppDmg = 15;

        System.out.println("You are " + name + ", a " + rank + ".");
        if (name.equals("Taiwyc")) {
            System.out.println("Born in the town of Eunsvale, you have trained since childhood to become a knight.");
            System.out.println("However, you were always interested in the ways of magic.");
            System.out.println("Now that your training as a knight is complete, you set off to the Kingdom of Leiryn.");
            System.out.println("You've been told there lives a powerful wizard.");
            System.out.println("Just as the kingdom becomes visible up ahead, a wild monster runs up and attacks!");
        }
        System.out.println("\nEnter anything to continue.");
        player.nextLine();

        battleGUI();

            System.out.println("After the battle, you notice something peculiar.");
            System.out.println("The monster, a Mire Wolf, seems to have been injured in a previous fight.");
            System.out.println("The wounds resemble large teeth marks and burned fur.");
            System.out.println("Cautiously, you continue on the path to the kingdom's gate.");

        System.out.println("\nEnter anything to continue.");
        player.nextLine();

            System.out.println("Finally, you've made it to Leiryn.");
            System.out.println("Let's explore the kingdom!");

        explore();
        firstDay();
        secondDay();
        thirdDay();
        wizardDecision();
        travel();
        dragon();
        finale();

    }

    public void battleGUI() {
        if (hp <= 0){
            System.out.println("You have fallen!");
            System.out.println("But, wanting to complete the journey fills you with determination!");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            hp = maxHp;
            battleGUI();
        }

        if (opphp > 0) {
            System.out.println("\nHealth: " + hp + "/" + maxHp);
            System.out.println("Stamina: " + stamina + "/" + maxStam);
            System.out.println("Mana: " + mana + "/" + maxMana);
            System.out.println("\nOpponent Health: " + opphp);
            System.out.println("What will you do?");

                System.out.println("\n1. Basic attack");
                System.out.println("2. Special attacks");
                if (magicFlag) {
                    System.out.println("3. Magic");
                    System.out.println("4. Rest");
                } else {
                    System.out.println("3. Rest");
                }

                answer = player.nextLine();
                if (magicFlag) {
                    while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")){
                        System.out.println("Please choose an action.");
                        answer = player.nextLine();
                    }
                }
                else {
                    while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3")){
                        System.out.println("Please choose an action.");
                        answer = player.nextLine();
                    }
                }

                if (answer.equals("1")) {
                    if (stamina >= 5) {
                        double temp = opphp;
                        if (mightFlag){
                            int tempDmg = mStat + dmg * 2;
                            shield = shield - tempDmg;
                        }
                        else shield = shield - dmg;
                        if (shield < 0)
                            opphp = opphp + shield;
                        double temp2 = temp - opphp;

                        System.out.println("You swung your sword at the opponent!");
                        System.out.println("Dealt " + temp2 + " damage!");
                        System.out.println("Used 5 stamina.");
                        stamina = stamina - 5;

                    } else {
                        System.out.println("You don't have enough stamina.");
                        System.out.println("\nEnter anything to go back.");
                        player.nextLine();
                        battleGUI();
                    }
                } else if (answer.equals("2")) {
                    System.out.println("Special attacks menu:");
                    System.out.println("(Enter 'back' to go back)");
                    System.out.println("\n1. " + s1);
                    if (s2 != null) {
                        if (s2.equals("Side Swing") && cooldown > 0) {
                            System.out.println("2. " + s2 + " (on cooldown)");
                        }
                        else {
                            System.out.println("2. " + s2);
                        }

                    }
                    answer = player.nextLine();

                    if (answer.equalsIgnoreCase("back")) {
                        battleGUI();
                    }

                    if (s2 != null){
                        while (!answer.equals("1") && !answer.equals("2")) {
                            System.out.println("Please choose a skill.");
                            answer = player.nextLine();
                            if (answer.equalsIgnoreCase("back")) {
                                battleGUI();
                            }
                        }
                    }
                    else {
                        while (!answer.equals("1")) {
                            System.out.println("Please choose a skill.");
                            answer = player.nextLine();
                            if (answer.equalsIgnoreCase("back")) {
                                battleGUI();
                            }
                        }
                    }

                    if (answer.equals("1")) {
                        if (s1.equals("Fast Swing")) fastSwing();

                        else efSwing();

                    }

                    else {
                        if (s2.equals("Side Swing")) sideSwing();

                        else if (s2.equals("Parry")) parry();

                    }

                }
                else if (answer.equals("3")) {
                    if (magicFlag) {
                        System.out.println("Magic menu:");
                        System.out.println("(Enter 'back' to go back)");
                        System.out.println("\n1. " + m1);
                        if (m2 != null) {
                            System.out.println("2. " + m2);
                        }
                        answer = player.nextLine();

                        if (answer.equalsIgnoreCase("back")) {
                            battleGUI();
                        }

                        if (m2 != null){
                            while (!answer.equals("1") && !answer.equals("2")) {
                                System.out.println("Please choose a skill.");
                                answer = player.nextLine();
                                if (answer.equalsIgnoreCase("back")) {
                                    battleGUI();
                                }
                            }
                        }
                        else {
                            while (!answer.equals("1")) {
                                System.out.println("Please choose a skill.");
                                answer = player.nextLine();
                                if (answer.equalsIgnoreCase("back")) {
                                    battleGUI();
                                }
                            }
                        }

                        if (answer.equals("1")) lightBall();
                        else {
                            if (m2.equals("Shadow Veil")) shadowVeil();
                            else if (m2.equals("Strength Boost")) strengthBoost();
                            else poison();
                        }

                    } else {
                        System.out.println("You take a moment to rest.");
                        System.out.println("Restored 5 stamina and 10 health.");
                        if (stamina < maxStam) {
                            stamina = stamina + 5;
                            if (stamina > maxStam) {
                                stamina = maxStam;
                            }
                        }

                        if (hp < maxHp) {
                            hp = hp + 10;
                            if (hp > maxHp) {
                                hp = maxHp;
                            }
                        }
                    }
                }
                   else if (magicFlag) {
                        System.out.println("You take a moment to rest.");
                        System.out.println("Restored " + 10*restore + " stamina, " + 5*restore + " mana. and " +
                                15*restore + " health.");
                        if (stamina < maxStam) {
                            stamina = stamina + 10*restore;
                            if (stamina > maxStam) {
                                stamina = maxStam;
                            }
                        }
                        if (mana < maxMana) {
                            mana = mana + 5*restore;
                            if (mana > maxMana) {
                                mana = maxMana;
                            }
                        }
                        if (hp < maxHp){
                            hp = hp + 15*restore;
                            if (hp > maxHp){
                                hp = maxHp;
                            }
                    }
                }

                System.out.println("Enter anything to continue.");
                player.nextLine();

        }
        if (opphp > 0) {

            if (s2 != null) {
                if (s2.equals("Side Swing") && cooldown > 0) cooldown--;
            }

            if (mightFlag) {
                if (cooldownM > 0) {
                    cooldownM--;
                }
                else {
                    mightFlag = false;
                    System.out.println("The might magic wears off.");
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                }
            }

            if (blightFlag) {
                if (cooldownB > 0) {
                    opphp = opphp - opphp * (.1 + bStat/1000.0);
                    cooldownB--;
                } else {
                    blightFlag = false;
                    System.out.println("The poison fades away.");
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                }
            }

            oppAction(n);
            battleGUI();
        }
        else {
            battleFlag = false;
            cooldown = 0;
            shield = 0;
            mightFlag = false;
            blightFlag = false;
            nightFlag = false;
        }
    }

    public void oppAction(int m) {
        if (m == 1) {
                if (p == 5) {
                    p++;
                    System.out.println("\nToltn: I've shown you the basic magic spells.");
                    System.out.println("Now, I'll start attacking back.");
                    System.out.println("Try to defeat me!\n");
                    System.out.println("\nEnter anything to continue.");
                    oppDmg = 10;
                    player.nextLine();
                } else if (p == 4) {
                    p++;
                    System.out.println("\nToltn: Now, for blight magic!");
                    System.out.println("I'll be casting 'poison', it won't hurt too much.");
                    System.out.println("However, you may feel a bit woozy.\n");
                    System.out.println("From Toltn's hands, a dark mist rises towards you!");
                    System.out.println("There's a bit of a pain in your chest, you're poisoned!");
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                    blightFlag = true;
                    cooldownB = 3;
                } else if (p == 3) {
                    p++;
                } else if (p == 2) {
                    p++;
                    System.out.println("\nToltn: Let me cast some might magic on you.");
                    System.out.println("It'll give you a chance against my Shadow Veil.");
                    System.out.println("Remember though, might magic only affects physical attacks.\n");
                    System.out.println("Toltn casts a spell, and you begin to feel stronger!");
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                    mightFlag = true;
                    cooldownM = 5;
                } else if (p == 1) {
                    p++;
                    System.out.println("\nToltn: Now it's time for some night magic!\n");
                    System.out.println("A shadowy veil appears around Toltn!");
                    System.out.println("\nToltn: You'll find you won't be able to damage me as much.\n");
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                    nightFlag = true;
                    cooldownN = 4;
                } else if (p == 0) {
                    p++;
                    System.out.println("\nToltn: Let me show you some light magic!\n");
                    System.out.println("A ball of light floats from his palm, and spins towards you!");
                    System.out.println("You deflect it with your sword, leaving a small burn mark on the weapon.");
                    System.out.println("\nToltn: Now, try concentrating on gathering energy in your hands.");
                    System.out.println("Since you're a knight, your training gives you an affinity for light magic.\n");
                    System.out.println("You concentrate, and a small ball of light forms in your hands!");
                    magicFlag = true;
                    m1 = "Light Ball";
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                }

                shield = 0;
                if (nightFlag) {
                    if (cooldownN > 0) {
                        shield = 25;
                        cooldownN--;
                    } else {
                        nightFlag = false;
                        shield = 0;
                        System.out.println("The shadowy veil dissapates.");
                        System.out.println("\nEnter anything to continue.");
                        player.nextLine();
                    }
                }

                if (battleFlag) {
                    if (Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6) > 0) {
                        hp = hp - Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6);
                    }
                    opphp = opphp - dmg * 1.6;
                    battleFlag = false;
                } else hp = hp - oppDmg;

                if (blightFlag) {
                    if (cooldownB > 0) {
                        hp = hp - hp * (.25);
                        cooldownB--;
                    } else {
                        blightFlag = false;
                        System.out.println("The poison fades away.");
                        System.out.println("\nEnter anything to continue.");
                        player.nextLine();
                    }
                }

                battleGUI();
        }
        else if (m == 2){
            shield = 15;
            if (oppFlag){
                if (oppCount > 0) {
                    shield += 50;
                    oppCount--;
                }
                else {
                    oppFlag = false;
                    System.out.println("The fire burns away.");
                }
            }
            int move = r.nextInt(7);
            if (move == 1) {
                System.out.println("The dragon creates a cloak of fire!");
                oppFlag = true;
                oppCount = 4;
            }
            else if (move > 2 && move < 5){
                System.out.println("The dragon breathes fire at you!");
                int temp = oppDmg;
                if (shoppeFlag == 2){
                    oppDmg -= 30;
                }
                if (battleFlag) {
                    if (Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6) > 0) {
                        hp = hp - Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6);
                    }
                    opphp = opphp - dmg * 1.6;
                    battleFlag = false;
                } else if (nightFlag) {
                    if (cooldownN > 0) {
                        hp = hp - (r.nextInt(oppDmg) + oppDmg) * (1 - nDmg / 100.0);
                        cooldownN--;
                    } else {
                        nightFlag = false;
                        System.out.println("The shadowy veil dissapates.");
                        System.out.println("\nEnter anything to continue.");
                        player.nextLine();
                    }
                } else hp = hp - (r.nextInt(oppDmg) + oppDmg);
                oppDmg = temp;
            }
            else{
                System.out.println("The dragon swipes their tail at you!");
                if (battleFlag) {
                    if (Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6) > 0) {
                        hp = hp - Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6);
                    }
                    opphp = opphp - dmg * 1.6;
                    battleFlag = false;
                } else if (nightFlag) {
                    if (cooldownN > 0) {
                        hp = hp - (r.nextInt(oppDmg) + oppDmg) * (1 - nDmg / 100.0);
                        cooldownN--;
                    } else {
                        nightFlag = false;
                        System.out.println("The shadowy veil dissapates.");
                        System.out.println("\nEnter anything to continue.");
                        player.nextLine();
                    }
                } else hp = hp - (r.nextInt(oppDmg) + oppDmg);
            }

        }

        else {
            shield = 0;
            System.out.println("The opponent attacks!");

            if (battleFlag) {
                if (Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6) > 0) {
                    hp = hp - Math.round((r.nextInt(oppDmg) + oppDmg) - (dmg/2.0) * 1.6);
                }
                opphp = opphp - dmg * 1.6;
                battleFlag = false;
            }
           else if (nightFlag) {
                if (cooldownN > 0) {
                    hp = hp - (r.nextInt(oppDmg) + oppDmg) * (1-nDmg/100.0);
                    cooldownN--;
                } else {
                    nightFlag = false;
                    System.out.println("The shadowy veil dissapates.");
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                }
            }
            else hp = hp - (r.nextInt(oppDmg) + oppDmg);
        }
    }

    public void fastSwing(){
        if (stamina >= 10) {
            System.out.println("You quickly swing your sword, catching your opponent off guard!");

            double temp = opphp;

            newDmg = dmg * 1.3;

            if (mightFlag){
                double tempDmg =  mStat + newDmg * 2;
                shield = shield - tempDmg;
            }
           else shield = shield - newDmg;
            if (shield < 0)
                opphp = opphp + shield;

            double temp2 = temp - opphp;

            System.out.println("Dealt " + temp2 + " damage!");
            System.out.println("Used 10 stamina.");
            stamina = stamina - 10;

        }
        else {
            System.out.println("You don't have enough stamina.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }
    }

    public void sideSwing() {
        if (stamina >= 15) {
            if (cooldown == 0) {

                double temp = opphp;
                newDmg = dmg * 2.5;
                if (mightFlag){
                    double tempDmg = mStat + newDmg * 2;
                    shield = shield - tempDmg;
                }
                else shield = shield - newDmg;
                if (shield < 0)
                    opphp = opphp + shield;

                double temp2 = temp - opphp;

                System.out.println("You swing the sword and aim at the opponents side!");
                System.out.println("Dealt " + temp2 + " damage!");
                System.out.println("Used 15 stamina. 1 turn cooldown.");

                stamina = stamina - 15;
                cooldown = 2;
            }

            else if (cooldown > 0)
            {
                System.out.println("The skill is on cooldown.");
                System.out.println("\nEnter anything to return.");
                player.nextLine();
                battleGUI();
            }
        }

        else {
            System.out.println("You don't have enough stamina.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }
    }

    public void parry(){
        if (stamina >= 15) {
            System.out.println("you hold your sword in a defensive position!");
            System.out.println("Reflected "+dmg*1.6+" damage!");
            System.out.println("Used 15 stamina.");
            battleFlag = true;
            stamina = stamina - 15;
        }
        else {
            System.out.println("You don't have enough stamina.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }
    }

    public void lightBall(){
        if (mana >= 5) {

            double temp = opphp;
            shield = shield - lDmg;
            if (shield < 0)
                opphp = opphp + shield;

            double temp2 = temp - opphp;

            System.out.println("You aim a ball of light at the opponent!");
            System.out.println("Dealt " + temp2 + " light damage!");
            System.out.println("Used 5 mana.");
            mana = mana - 5;

        }
        else {
            System.out.println("You don't have enough mana.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }
    }

    public void explore() {
        System.out.println("\nWhere would you like to go?");
        System.out.println("1. Forward");
        System.out.println("2. Left");
        System.out.println("3. Right");
        answer = player.nextLine();

        while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3")) {
            System.out.println("Choose a route.");
            answer = player.nextLine();
        }

            if (answer.equals("1")) {
                System.out.println("You head forward on the main street.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("On the street there are horse-drawn carriages and workers rolling wagons.");
                System.out.println("Meanwhile the sidewalk has families going in and out from the stores on the side.");
                System.out.println("One particular shop catches your eye.");
                System.out.println("'Fory's Armory', you peer into the window of the building.");
                System.out.println("It seems to hold all kinds of weapons.");
                System.out.println("You decide to browse their wares for a bit.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("Entering the shop, you look around.");
                System.out.println("Immediately the sword section catches your eye.");
                System.out.println("Looking at your own sword, it does look quite worn.");
                System.out.println("You pick out two swords and ask the manager if you can test them out.");
                System.out.println("The manager is an old lady with an eyepatch.");
                System.out.println("\nManager: We have a room in the back for that, it's the door right next to you.\n");
                System.out.println("You go to try out the swords.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("There are some training dummies spaced out in a room.");
                System.out.println("You go up to one and pick out a sword at random to use.");
                System.out.println("The sword gives you inspiration for a new special attack.");
                System.out.println("Let's try each of them out!");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                opphp = 50;
                oppDmg = 10;
                madDummy();
                System.out.println("After testing them out in battle, which sword do you choose?");
                System.out.println("1. Side Swing Sword");
                System.out.println("2. Parry Sword");
                System.out.println("3. None");
                answer = player.nextLine();

                while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3")){
                    System.out.println("\nManager: Pick a sword.");
                    answer = player.nextLine();
                }

                if (answer.equals("1")) {
                    s2 = "Side Swing";
                    System.out.println("You choose the first sword.");
                    System.out.println("\nManager: That'll be 50 gold coins. Have a good day.");
                    gold = gold - 50;
                } else if (answer.equals("2")) {
                    s2 = "Parry";
                    System.out.println("You choose the second sword.");
                    System.out.println("\nManager: That'll be 50 gold coins. Have a good day.");
                    gold = gold - 50;
                } else {
                    System.out.println("You decide to keep your current sword.");
                    System.out.println("\nManager: Very well. Have a good day.");
                }
            }

            else if (answer.equals("2")) {
                System.out.println("You take the curved road to your left.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("The road contains many stalls of produce on the side.");
                System.out.println("It seems to be a farmer's market.");
                System.out.println("\nVendors: Fresh fruit! Baked bread! Gallons of milk!\n");
                System.out.println("You do feel a bit hungry, so you decide to buy some food.");
                System.out.println("In total, you spend around 25 gold, buying enough food to last a few days.");
                gold = gold - 25;
                System.out.println("Taking the first bite into the food, you feel like you became healthier!");
                maxHp = maxHp + 15;
                farmerFlag = true;

            } else {
                System.out.println("You take the curved road to your right.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("You walk along the road for a while.");
                System.out.println("There's not much that catches your eye.");
                System.out.println("Except...for the big building in the distance.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("The building appears to be a library.");
                System.out.println("You wonder if they have any books on magic.");
                System.out.println("Scouring the magic category, you find many copies of a biography.");
                System.out.println("Aldruic Toltn: Wizard of Wonder.");
                System.out.println("Perhaps this is the wizard you're looking for?");
                System.out.println("The book is about his accomplishments in magic.");
                System.out.println("After reading the book, you feel like you understand magic more.");
                maxMana = maxMana + 10;
            }

        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        System.out.println("It's getting late out, so you decide to find a place to sleep for the night.");
        System.out.println("You find a tavern that offers rooms for their customers.");
        System.out.println("\nBarkeep: 25 coins a night. Dinner is 10 gold per plate.\n");
        System.out.println("Counting your coins, you only have enough for a few nights.");
        System.out.println("In the morning, you'll need to find some work to do.");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        inn();
    }

    public void madDummy() {

        System.out.println("The dummy...comes to life!?");
        System.out.println("\nManager: I forgot to mention they're made of magical wood.");
        System.out.println("I got a wizard to make them act like actual battle opponents.");
        System.out.println("Don't worry, they're not too tough. Good luck.");
        for (int i = 1; i <= 2; i++) {
            System.out.println("\nOpponent's health: " + opphp);
            System.out.println("\nSpecial skills menu:");

            if (i == 1) {
                s2 = "Side Swing";
            }
            else {
                s2 = "Parry";
            }

            System.out.println("\n1. " + s2);
            answer = player.nextLine();

            while (!answer.equals("1")) {
                System.out.println("Please choose an option.");
                answer = player.nextLine();
            }

            if (s2.equals("Side Swing")) {
                newDmg = dmg * 2.5;
                System.out.println("You swing the sword and aim at the opponents side!");
                System.out.println("Dealt " + newDmg + " damage!");
                System.out.println("Used 15 stamina. 1 turn cooldown.");
                opphp = opphp - newDmg;
            }
            else if (s2.equals("Parry")) {
                System.out.println("you hold your sword in a defensive position!");
                System.out.println("Reflected " + dmg*1.6+ " damage!");
                System.out.println("Used 15 stamina.");
                opphp = opphp - dmg*1.6;
            }

            System.out.println("\nEnter anything to continue.");
            player.nextLine();

        }
    }

    public void inn(){
        if (day > 0) {
            System.out.println("It's getting late. You haven't eaten much today.");
            if (farmerFlag) {
                System.out.println("Using the small stove in your room, you cook some food.");
                System.out.println("The hard work farmers do makes you appreciate the meal more.");
            } else {
                System.out.println("You order some dinner from the tavern.");
                gold = gold - 10;
            }

            System.out.println("You have " + gold + " gold left.");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            if (day == 2){
                System.out.println("\nBarkeep: The rent's been increased to 45 gold a night.");
                System.out.println("Look, I don't like it either, but there's less people coming in.");
                System.out.println("More people are hiding in their houses, afraid that beasts will invade Leiryn.");
                System.out.println("Dinner's still 10 gold a plate, though.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
            }
        }

        if (day < 2 && gold >= 25) {
            gold = gold - 25;
            maxHp = maxHp + 10;
            hp = maxHp;
            dmg = dmg + 5;
            mana = maxMana;
            maxStam = maxStam + 10;
            stamina = maxStam;
            System.out.println("It's morning at the tavern.");
            System.out.println("After a good night's sleep, you feel like your body has improved.");
        }
        else if (day >= 2 && gold >= 45){
            gold = gold - 45;
            maxHp = maxHp + 10;
            hp = maxHp;
            dmg = dmg + 5;
            mana = maxMana;
            maxStam = maxStam + 10;
            stamina = maxStam;
            System.out.println("It's morning at the tavern.");
            System.out.println("After a good night's sleep, you feel like your body has improved.");
        }
        else {
            hp = maxHp/2;
            mana = maxMana/2;
            stamina = maxStam/2;
            System.out.println("You can't afford to stay the night at the tavern.");
            System.out.println("You decide to find a place to sleep outside.");
            System.out.println("After a restless sleep, you feel barely awake.");
            System.out.println("Hopefully the requests today will give you enough money.");
        }
            if (day == 0){
                System.out.println("The tavern seems pretty busy today.");
                System.out.println("Many mercenaries are looking at the requests pinned on the board.");
            }
            else if (day == 1){
                System.out.println("There's not a lot of people at the tavern today.");
                System.out.println("Still, there are a lot of mercenaries looking at requests.");
            }
            else if (day == 2){
                System.out.println("Just like the barkeep said, there seems to be barely any people in today.");
                System.out.println("The request board is bare as well.");
                System.out.println("Guess the mercenaries took all the remaining requests.");
            }
            if (day < 2) System.out.println("You take a look at the requests today as well.");
            day++;
    }

    public void firstDay(){
        System.out.println("\nWhich one do you want to do?");
        System.out.println("1. Lost rabbit, 15 gold.");
        System.out.println("2. Monsters near the gate, 30 gold.");
        answer = player.nextLine();
        while (!answer.equals("1") && !answer.equals("2")){
            System.out.println("Choose a quest.");
            answer = player.nextLine();
        }
        if (answer.equals("1")){
            System.out.println("'A rabbit named Yopp has ran away.");
            System.out.println("He was last seen around the gardens near the plaza.");
            System.out.println("If you find him, please take him to the Amazing Armour Shoppe.'");
            System.out.println("Request made by: Ryi");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("You head to the plaza.");
            System.out.println("St. Lex Plaza, named for the citadel that used to be where the plaza is now.");
            System.out.println("There are many people sitting around here, relaxing in view of the fountain.");
            System.out.println("Food vendors are just outside the plaza, customers in line.");
            System.out.println("But you don't have time to rest now, you need to find a rabbit.");
            System.out.println("You head towards the gardens to the left of the plaza.");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("The garden is filled with exotic flowers and trees.");
            System.out.println("Looking closely at one of the flowers, you notice bite marks.");
            System.out.println("Looks like a rabbit has been here.");
            if (farmerFlag){
                System.out.println("Judging by how the rabbit didn't finish any flowers...");
                System.out.println("The plants here must not be to the rabbit's taste.");
                System.out.println("You take out one of the vegetables you bought and set it down under a tree.");
            }
            else {
                System.out.println("Bite marks and rabbit tracks lead to a den underneath a tree.");
                System.out.println("The gardeners won't be too happy about this.");
                System.out.println("You wonder if the rabbit is hungry by now.");
                System.out.println("You quickly run to one of the food stalls.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("\nVendor: Salads are 5 gold.\n");
                gold = gold - 5;
                System.out.println("Salad in hand, you place it down near the tree den.");
            }
            System.out.println("You hide behind the tree and wait.");
            System.out.println("Sure enough, a white rabbit bounces towards the food and begins eating.");
            System.out.println("You quickly catch the rabbit!");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("You stand in front of the Amazing Armour Shoppe.");
            System.out.println("Rabbit in hand, you walk into the store.");
            System.out.println("A child is sitting behind the counter, eyes widening at the sight of the rabbit.");
            System.out.println("\nChild: You found Yopp! You saw the request?\n");
            System.out.println("You nod and show the child the quest paper.");
            System.out.println("The child takes the paper and hands you 15 gold.");
            System.out.println("\nChild: Here's your payment, I'm Ryi.");
            System.out.println("I might put up some more requests soon, thank you!\n");
            System.out.println("You thank Ryi for the gold and walk back to the tavern.");
            shoppeFlag++;
        }
        else {
            System.out.println("'There have been numerous recent sightings of monsters near the walls.");
            System.out.println("The monsters are getting fiercer and our guards are becoming exhausted.");
            System.out.println("We need some warriors to help out our squad. There will be compensation.'");
            System.out.println("Signed, Leoyd Enwrou, Captain of Defense Squad 3");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("You head to the Defense Camp, located near the west side of the kingdom.");
            System.out.println("There is a circular building in the center, surrounded by big tents.");
            System.out.println("You enter the building.");
            System.out.println("A gruff man is sitting behind a desk.");
            System.out.println("\nMan: You got business here?\n");
            System.out.println("You show the man the quest paper.");
            System.out.println("\nMan: If you're looking for Captain Enwrou, he's in the white tent to the left.\n");
            System.out.println("You thank the man and head towards the tent.");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("\nEnwrou: -need more troops to defend agains- Huh? Who're you?\n");
            System.out.println("You explain that you're here to help the guards fight back.");
            System.out.println("\nEnwrou: Ah, you saw the request. Welcome aboard, you're just in time.");
            System.out.println("We're just about to head out and scare off some monsters.");
            System.out.println("Follow my commands and stay in line with the other troops.\n");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("You and Defense Squad 3 are now outside the western wall of Leiryn.");
            System.out.println("There are already wolves and other beasts prowling around the land.");
            System.out.println("\nEnwrou: It didn't use to be like this, something's driving these creatures here.");
            System.out.println("But they're endangering our people, so we have to do something about it.\n");
            System.out.println("He signals the troops, and they, including you, rush into battle.");
            System.out.println("At the sight of the troops, some of the monsters run away.");
            System.out.println("Others, however, start rushing towards the guards.");
            System.out.println("You and the others prepare to battle!");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            battleOne();
            System.out.println("After the battle, everyone is exhausted.");
            System.out.println("The beasts have been driven out further to keep the people of Leiryn safe.");
            System.out.println("\nEnwrou: As long as whatever's keeping them out of their turf is still there...");
            System.out.println("I'm thinking of sending a scout to see what's happening with the monsters.");
            System.out.println("Thanks for your work, here's your payment.");
            gold = gold + 30;
            troopFlag++;
        }
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        inn();
    }

    public void battleOne(){
        opphp = 65;
        oppDmg = 10;
        battleGUI();
        System.out.println("The boar falls to the ground.");
        System.out.println("Like the wolf from yesterday, it's covered in burn marks.");
        System.out.println("You wonder just what kind of monster is scaring these beasts.");
        System.out.println("Suddenly, another beast runs up!");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        opphp = 70;
        oppDmg = 9;
        battleGUI();
    }

    public void secondDay(){
        System.out.println("\nWhich one do you want to do?");
        System.out.println("1. Farmers attacked by wolves, 60 gold.");
        System.out.println("2. Supply delivery, 30 gold.");
        answer = player.nextLine();
        while (!answer.equals("1") && !answer.equals("2")) {
            System.out.println("Choose a quest.");
            answer = player.nextLine();
        }
        if (answer.equals("1")){
            System.out.println("'We've received reports from farmers along the eastern wall.");
            System.out.println("They have been attacked by Mire Wolves and need assistance.");
            System.out.println("The defense squads are already spread thin, we can't spare many troops.");
            System.out.println("We need some warriors to help out the farmers. There will be compensation.'");
            System.out.println("Signed, Leoyd Enwrou, Captain of Defense Squad 3");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            if (troopFlag == 1){
                System.out.println("You head to the Defense Camp's main building.");
                System.out.println("There's a white-haired man sitting behind the desk today.");
                System.out.println("\nMan: Welcome, what are you here for?\n");
                System.out.println("You explain that you're looking for Captain Enwrou.");
                System.out.println("\nMan: Captain Enwrou is in the white tent to the left. That's his tent.\n");
                System.out.println("You thank the man and head towards the tent.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("Captain Enwrou looks deep in thought.");
                System.out.println("\nEnwrou: What to do...Oh! It's you again. Welcome back.");
                System.out.println("Ready to save some crops and animals from hungry wolves?.\n");
                System.out.println("You nod and he gives you directions to the farm patch on the east.");
            }
            else {
                System.out.println("You head to the Defense Camp, located near the west side of the kingdom.");
                System.out.println("There is a circular building in the center, surrounded by big tents.");
                System.out.println("You enter the building.");
                System.out.println("A white-haired man is sitting behind a desk.");
                System.out.println("\nMan: Welcome, what are you here for?\n");
                System.out.println("You show the man the quest paper.");
                System.out.println("\nMan: If you're looking for Captain Enwrou, he's in the white tent to the left.\n");
                System.out.println("You thank the man and head towards the tent.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                System.out.println("\nEnwrou: Hmm...What to do... Huh? Who're you?\n");
                System.out.println("You explain that you're here to help the farmers fight back.");
                System.out.println("\nEnwrou: Saw the request, right? Well I'll let you get to it.");
                System.out.println("Here's where the farm patch is, report back for your reward.\n");
            }
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("The farm patch is in disarray.");
            System.out.println("Animals have been stolen by wolves, crops have been eaten.");
            System.out.println("Most people are hiding in their barns.");
            System.out.println("A farmer walks up to you.");
            System.out.println("\nFarmer: Are you here to deal with the wolves?\n");
            System.out.println("You nod and say you were sent by Captain Enwrou.");
            System.out.println("\nFarmer: Finally! That captain does take his sweet time.");
            System.out.println("We've been terrorized by wolves since a week ago.");
            System.out.println("Some of our young sprouts are still fighting, I'll lead you to them.\n");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("There are a group of people surrounded by wolves.");
            System.out.println("\nFarmer: They're in trouble! Please help them!\n");
            System.out.println("You run into the fray!");
            wolfBattle();
            System.out.println("The wolves start to run away.");
            System.out.println("\nFarmer: Thank you, brave knight!");
            System.out.println("How can we repay you?\n");
            System.out.println("You reply that you don't need any reward from them, but the farmer insists.");
            System.out.println("\nFarmer: We have some food stored for the market, we could share some with you.\n");
            System.out.println("Thanking the farmer, you return to the Defense Camp.");
            farmerFlag = true;
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("\nEnwrou: Welcome back. Glad to see you in one piece.");
            System.out.println("Here's your payment. 60 gold.");
            System.out.println("Now, I have some work to do, you'd best be on your way too.");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            gold = gold + 60;
            troopFlag++;
        }
        else {
            System.out.println("'There are heavy supplies that need to be picked up.");
            System.out.println("Go to the Amazing Armour Shoppe first.");
            System.out.println("There will be more details there.'");
            System.out.println("Request made by: Ryi");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("You head to the Amazing Armour Shoppe.");
            if (shoppeFlag == 1){
                System.out.println("You greet Ryi and mention the request.");
                System.out.println("\nRyi: You're helping out again? Thanks!");
                System.out.println("My teacher ordered something from Gems Showcase.");
                System.out.println("But, when I tried to carry it, it was too heavy.");
                System.out.println("Could you carry it for me and bring it here?\n");
                System.out.println("You agree to the task.");
            }
            else {
                System.out.println("You enter the store and see a child behind the counter.");
                System.out.println("\nChild: Welcome! Are you here to buy something?\n");
                System.out.println("You mention a request about a supply delivery.");
                System.out.println("\nChild: Oh, you're here for that. I'm Ryi, the requester.");
                System.out.println("So the owner of this shoppe ordered something from Gems Showcase.");
                System.out.println("It's too heavy for me to carry, that's where you come in.");
                System.out.println("You nod and ask where to find Gems Showcase.");
            }
            System.out.println("\nRyi: Here's where Gems Showcase is, good luck!\n");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("Gems Showcase, An extravagent store with jewels embedded in the walls.");
            System.out.println("Inside is filled with display cases of crystals and panels of information.");
            System.out.println("\nCashier: Hello, can I help you?\n");
            System.out.println("You say that you're here to pick up a delivery for Amazing Armour Shoppe.");
            System.out.println("\nCashier: You'll have to speak to the owner about that.");
            System.out.println("I'll let him know, please wait here for a moment.\n");
            System.out.println("The cashier disappears into a backroom.");
            System.out.println("While waiting, you look around at some of the panels.");
            System.out.println("Apparently, some gems have the power to enhance magic.");
            System.out.println("There's even a theory that crystals are the solid form of magic.");
            System.out.println("You feel like you understand magic a bit better.");
            maxMana = maxMana + 10;
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            System.out.println("An extravagent man appears from the backroom.");
            System.out.println("\nOwner: Sorry about the wait, you're the representative from the armour shop, yes?\n");
            System.out.println("You say you're just here to pick up a delivery.");
            System.out.println("\nOwner: Either way, welcome! My name is Raen, I own Gems Showcase.");
            System.out.println("We've been expecting someone from the armour shop to inquire about a package.");
            System.out.println("A child came here earlier and tried to carry it, poor thing.");
            System.out.println("It is a heavy package though, I doubt the average adult could even carry it.");
            System.out.println("If it proves too heavy, I could have one of my employees help you.\n");
            System.out.println("He shows you the package in the storage room.");
            System.out.println("You try lifting it up.");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            if (dmg >= 25){
                System.out.println("You lift it up with ease.");
                System.out.println("\nOwner: Bravo! Just in case, I'll send someone to carry it with you.");
            }
            else {
                System.out.println("Straining yourself, you manage to lift it.");
                System.out.println("You can feel your muscles being pulled.");
                System.out.println("That's going to hurt in the morning.");
                System.out.println("\nOwner: Don't push yourself too hard, I'll get some help.");
                maxHp = maxHp - 15;
            }
            System.out.println("With two people carrying the box, the work becomes a lot easier.");
            System.out.println("You haul the package all the way to Amazing Armour Shoppe");
            System.out.println("Thanking the employee, you push the box into the store.");
            System.out.println("\nRyi: You got the package!");
            System.out.println("You can just leave it there, my teacher will take care of it from here.");
            System.out.println("Here's your payment, 30 coins.\n");
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
            gold = gold + 30;
            shoppeFlag++;

            if (shoppeFlag == 2){
                System.out.println("You decide to talk with Ryi for a bit.");
                System.out.println("\nRyi: Hmm? Why am I working here?");
                System.out.println("Well, I had to get an apprenticeship somewhere.");
                System.out.println("My teacher knew my parents, and she decided to take me in.");
                System.out.println("It's fun, actually, learning how armour is made.");
                System.out.println("How about you, Taiwyc? You don't seem to be from Leiryn, what brought you here?\n");
                System.out.println("You explain that you're on a quest to learn magic.");
                System.out.println("You heard there was a wizard living here and wanted to learn from them.");
                System.out.println("\nRyi: A wizard...hmm...if you come back tomorrow, I might be able to help.\n");
                System.out.println("Before you can ask Ryi what he means by that, he stands up.");
                System.out.println("\nRyi: I better close the store, come by tomorrow if you can!\n");
                System.out.println("You head back to the tavern.");
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
            }
        }
        inn();
    }

    public void wolfBattle(){
        opphp = 100;
        oppDmg = 15;
        battleGUI();
        System.out.println("After defeating the wolf, you rest for a bit.");
        System.out.println("Another wolf leaps towards you before getting blocked by a broom.");
        System.out.println("\nYoung Farmers: Thanks for your help! We'll do what we can!\n");
        System.out.println("You continue to fight!");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        stamina = maxStam;
        opphp = 105;
        oppDmg = 10;
        battleGUI();
    }


    public void thirdDay() {

        System.out.println("\nWhat do you want to do?");

        if (troopFlag > 0){
            System.out.println("1. Visit the Defense Camp.");
        }
        if (shoppeFlag == 2){
            System.out.println("2. Visit the Amazing Armour Shoppe.");
        }
        answer = player.nextLine();

        if (shoppeFlag == 2 && troopFlag == 0){
            while (!answer.equals("2")) {
                System.out.println("Choose an action.");
                answer = player.nextLine();
            }
        }

       else while (!answer.equals("1")) {
            System.out.println("Choose a quest.");
            answer = player.nextLine();
        }

     if (answer.equals("1")){
             System.out.println("You head towards the Defense Camp.");
             if (troopFlag > 1){
                 System.out.println("Before going into Captain Enwrou's tent, you notice the state of the base.");
                 System.out.println("There are papers and armour lying around.");
                 System.out.println("Injured guards are hastily carried into cramped medical tents.");
                 System.out.println("You worry about how the squads will handle any future attacks");
                 System.out.println("You prepare to head into the tent.");
             }
             else {
                 System.out.println("A brown-haired lady is sitting behind the desk today.");
                 System.out.println("\nLady: Welcome, what brings you here?\n");
                 System.out.println("You ask where Captain Enwrou is.");
                 System.out.println("\nLady: He'll be in the white tent outside.\n");
                 System.out.println("You thank the lady and head towards the tent.");
             }
             System.out.println("\nEnter anything to continue.");
             player.nextLine();
             System.out.println("\nEnwrou: -really going up against this...");
             System.out.println("Oh, Taiwyc, welcome.");
             System.out.println("I just received the report from the scout.");
             System.out.println("It's not pleasant news, you'd best sit down for this.");
             System.out.println("The cause of the beasts rampaging towards Leiryn...");
             System.out.println("Is a dragon, that has made its home in the eastern mountains.\n");
             System.out.println("\nEnter anything to continue.");
             player.nextLine();
             System.out.println("Enwrou: Since, we are dealing with a threat that could destroy all of Leiryn,");
             System.out.println("I brought someone who will be a powerful asset to the protection of our kingdom.");
             System.out.println("As you're here, I would like you to meet him.");
             System.out.println("Let's head out into the training field, he'll be waiting for us there.\n");
             System.out.println("You and Captain Enwrou head towards the training field.");
             System.out.println("It is a big area, with training dummies on one side and an arena on the other.");
             System.out.println("An old man stands in the middle of the arena.");
             System.out.println("\nEnwrou: Taiwyc, meet Mr. Aldruic Toltn, Leiryn's own wizard.");
             System.out.println("\nEnter anything to continue.");
             player.nextLine();
             System.out.println("\nToltn: So, this is the cadet you chose to learn magic?");
             System.out.println("\nEnwrou: Not a cadet, but a powerful fighter that I think could help us.");
             System.out.println("\nToltn: Greetings, then. My question to you is, do you want to learn magic?\n");
             System.out.println("You enthusiastically say that learning magic has been your goal in Leiryn.");
             System.out.println("\nToltn: Haha, very well. Let's begin our first lesson now.");
             System.out.println("He takes a few steps backwards, and readies his magic!");
             System.out.println("\nEnter anything to continue.");
             player.nextLine();
             wizard();
             System.out.println("\nEwnrou: Will Taiwyc be able to take down the dragon?");
             System.out.println("\nToltn: Hard to say for certain, but after we go through some training...");
             System.out.println("Taiwyc, due to the urgency of the situaion,");
             System.out.println("I only have time to strengthen what you know, and teach you one spell.");
             System.out.println("What spell will it be?");

     }

     else {
         System.out.println("You decide to visit the Amazing Armour Shoppe.");
         System.out.println("When you get to the store, Ryi is standing outside.");
         System.out.println("\nRyi: Ah, Taiwyc! I was just about to go look for you!");
         System.out.println("We have a special guest in the shoppe today.");
         System.out.println("Come on, let's go in!\n");
         System.out.println("\nEnter anything to continue.");
         player.nextLine();
         System.out.println("You enter the shoppe.");
         System.out.println("An old, bearded man is sitting behind the counter today.");
         System.out.println("\nMan: Ah, is this the knight that has been helping Ms. Wodric with the shoppe?");
         System.out.println("My name is Aldruic Toltn, I heard you've been looking for a wizard.\n");
         System.out.println("Ryi: Mr. Toltn is old friends with my teacher, and he can help you learn magic!");
         System.out.println("\nToltn: Taiwyc, was it? We should go to a more appropriate training ground.\n");
         System.out.println("Toltn heads towards a door. You and Ryi follow along.");
         System.out.println("The door leads downstairs, where there's a spacious room.");
         System.out.println("Toltn turns towards you, and readies his magic!");
         System.out.println("\nEnter anything to continue.");
         player.nextLine();
         wizard();
         System.out.println("\nRyi: Will Taiwyc be able to be your apprentice?");
         System.out.println("\nToltn: Of course! But, I am a busy wizard.");
         System.out.println("I'll offer you this, I can teach you one spell from the battle we did.");
         System.out.println("Give it some thought.");
     }
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
    }

    public void wizard(){
        lDmg = 25;
        nDmg = 10;
        mStat = 19;
        bStat = 7;

        for (int i = 0; i < troopFlag; i++){
            lDmg = lDmg + 2;
            mStat = mStat + 2;
        }

        for (int i = 0; i < shoppeFlag; i++){
            lDmg = lDmg + 1;
            nDmg = nDmg + 1;
            mStat = mStat + 1;
            bStat = bStat + 1;
        }

        System.out.println("\nToltn: So, you wanna learn magic, eh?");
        System.out.println("First, let me explain the basics of magic.");
        System.out.println("There are 4 major parts of magic.");
        System.out.println("First, we have light magic, a class of magic meant for attack.");
        System.out.println("Then, we have night magic, a counter to light magic.");
        System.out.println("Finally, we have might and blight magic.");
        System.out.println("Might magic boosts power, while blight magic inflicts ailments.");
        System.out.println("You got all that? Because we're about to do a demonstration.\n");
        opphp = 500;
        oppDmg = 0;
        n = 1;
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        battleGUI();
        System.out.println("Toltn quickly counters your sword.");
        System.out.println("\nToltn: Hah, that was a fun battle.");
        System.out.println("Well done, Taiwyc.");

    }

    public void wizardDecision(){
        System.out.println("Which spell do you want to learn?");
        System.out.println("1. Shadow Veil");
        System.out.println("2. Strength Boost");
        System.out.println("3. Poison");
        System.out.println("4. None");

        String answer = player.nextLine();
        while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")){
            System.out.println("Please choose an option.");
            answer = player.nextLine();
        }

        if (answer.equals("4")){
            System.out.println("Totln: Very well, I shall focus on your light magic and strength.");
        }

        else{
            if (answer.equals("1")){
                m2 = "Shadow Veil";
            }
            else if (answer.equals("2")){
                m2 = "Strength Boost";
            }
            else {
                m2 = "Poison";
            }

            System.out.println("Totln: Very well.");
        }
        System.out.println("Let the training begin!");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
    }

    public void shadowVeil(){
        if (mana >= 10) {
            mana = mana - 10;
            System.out.println("You cast a shadowy veil on yourself!");
            nightFlag = true;
            if (cooldownN == 0){
                cooldownN = 4;
            }
            System.out.println("Used 10 mana.");
        }
        else {
            System.out.println("You don't have enough mana.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }

    }

    public void strengthBoost(){
        if (mana >= 10) {
            mana = mana - 10;
            System.out.println("You boost your strength with magic!");
            mightFlag = true;
            if (cooldownM == 0){
                cooldownM = 5;
            }
            System.out.println("Used 10 mana.");
        }
        else {
            System.out.println("You don't have enough mana.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }

    }

    public void poison(){
        if (mana >= 15) {
            mana = mana - 15;
            System.out.println("You poison the opponent!");
            blightFlag = true;
            if (cooldownB == 0){
                cooldownB = 4;
            }
            System.out.println("Used 15 mana.");
        }
        else {
            System.out.println("You don't have enough mana.");
            System.out.println("\nEnter anything to go back.");
            player.nextLine();
            battleGUI();
        }

    }

    public void travel(){
        restore = 5;
        System.out.println("It's been a few weeks.");
        System.out.println("So far, your training includes magic studies and assisting the town.");
        maxHp += 500;
        hp = maxHp;
        maxStam += 50;
        stamina = maxStam;
        maxMana += 50;
        mana = maxMana;
        lDmg += 45;

        dmg = dmg + 45;
        if (m2 == null){
            lDmg += 55;
            dmg = dmg + 10;
        }
        else if (m2.equals("Shadow Veil")){
            nDmg = 65;
        }
        else if (m2.equals("Strength Boost")){
            mStat = 50;
        }
        else bStat = 30;

        if (s2 == null){
            s1 = "Even Faster Swing";
        }

        System.out.println("A lot of people have fled to neighboring kingdoms.");
        System.out.println("Monsters are still roaming around Leiryn's territory, and they move closer everyday.");
        System.out.println("You patrol the east for monsters.");
        System.out.println("There are a few wolves and boars straying too close to the kingdom.");
        System.out.println("Before you can do anything, one of them spots you and attacks!");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        n = 0;
        opphp = 450;
        oppDmg = 25;
        battleGUI();
        System.out.println("The other monsters start running away.");
        System.out.println("You report back to Toltn.");
        System.out.println("\nToltn: I see, the situation hasn't gotten any better.");
        if (shoppeFlag == 2){
            System.out.println("I've heard from my friend that the monsters are coming here due to a dragon.");
            System.out.println("He asked me for assistance in dealing with the dragon.");
            System.out.println("However, I believe there's a better candidate.");
        }
        else {
            System.out.println("I'm afraid it won't get better until we deal with that dragon.");
            System.out.println("But, I think you're ready.");
        }
        System.out.println("Pack your bags, and prepare for a journey.");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        if (shoppeFlag == 2) {
            System.out.println("\nRyi: I heard you're going on a quest to the dragon.");
            System.out.println("It's going to be tough, right?");
            System.out.println("In that case, you should take some fire-proof armour!");
            System.out.println("Really, it's fine, we'll do anything we can to help you.");
            System.out.println("After all, we're a part of Leiryn too.");
            maxHp = maxHp + 100;
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
        }
        else if (troopFlag == 2) {
            System.out.println("\nLeoyd: Well, Taiwyc, I heard from Aldruic.");
            System.out.println("You're going off to fight the dragon.");
            System.out.println("Here, take this sword, I'm sure it's better than your worn out sword.");
            System.out.println("I'm grateful for all that you've done for this kingdom.");
            System.out.println("Good luck out there.");
            dmg = dmg + 20;
            System.out.println("\nEnter anything to continue.");
            player.nextLine();
        }
        System.out.println("You set off on your journey to save the kingdom of Leiryn.");
        hp = maxHp;
        stamina = maxStam;
        mana = maxMana;
        p = 0;
        while (p < 5) {
             if (p == 4){
                System.out.println("You can see the dragon's den ahead.");
                System.out.println("This is it, the final battle.");
                System.out.println("Before you go in, you should rest.");
                System.out.println("Do you want to rest? (y/n)");
                answer = player.nextLine();
                while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes") &&
                        !answer.equalsIgnoreCase(("n")) && !answer.equalsIgnoreCase("no")){
                    System.out.println("Please answer yes or no.");
                    answer = player.nextLine();
                }
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")){
                    System.out.println("You take a moment to sit down.");
                    System.out.println("Restored all health, stamina, and mana.");
                    hp = maxHp;
                    stamina = maxStam;
                    mana = maxMana;
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                }
                else {
                    System.out.println("You go in the den.");
                }
            }
            else if (p == 3){
                System.out.println("After scaring off the wolves, you start to climb the mountain.");
                System.out.println("The path is dangerous, but you manage to keep climbing.");
                System.out.println("You make it midway up the mountain.");
                System.out.println("The temperature gets warmer near the top, possibly from the dragon.");
                System.out.println("Suddenly, you hear a rumbling sound.");
                System.out.println("The boulder in front of you starts moving to reveal a golem.");
                System.out.println("The golem prepares to strike!");
                 opphp = 600;
                 oppDmg = 25;
                 System.out.println("\nEnter anything to continue.");
                 player.nextLine();
                 battleGUI();
            }
            else if (p == 2){
                System.out.println("The forest starts to thin out.");
                System.out.println("The trees that are here look charred.");
                System.out.println("There are claw marks on the ground.");
                System.out.println("You follow the claw marks.");
                System.out.println("They lead you to a pack of aggressive wolves.");
                System.out.println("You prepare to fight!");
                opphp = 500;
                oppDmg = 27;
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                battleGUI();
            }
            else if (p == 1) {
                 System.out.println("You drive off the birds and continue on your way.");
                 System.out.println("You pass a river surrounded by elk.");
                 System.out.println("The elk seem to be living peacefully.");
                 System.out.println("This seems like a good place to rest.");
                 System.out.println("Do you want to rest? (y/n)");
                 answer = player.nextLine();
                 while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes") &&
                         !answer.equalsIgnoreCase(("n")) && !answer.equalsIgnoreCase("no")) {
                     System.out.println("Please answer yes or no.");
                     answer = player.nextLine();
                 }
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")){
                    System.out.println("You take a moment to sit down and enjoy the scenery.");
                    System.out.println("Restored all health, stamina, and mana.");
                    hp = maxHp;
                    stamina = maxStam;
                    mana = maxMana;
                    if (hp > maxHp) hp = maxHp;
                    if (stamina > maxStam) stamina = maxStam;
                    if (mana > maxMana) mana = maxMana;
                    System.out.println("\nEnter anything to continue.");
                    player.nextLine();
                }
                else {
                    System.out.println("You continue your journey.");
                }

            }
           else if (p == 0) {
                System.out.println("You must travel through the forest and into the mountains.");
                System.out.println("Many monsters have made their home in the forest.");
                System.out.println("You avoid as many of the monsters as you can, but a flock of Flame Birds notice you!");
                System.out.println("You prepare to fight!");
                opphp = 475;
                oppDmg = 30;
                System.out.println("\nEnter anything to continue.");
                player.nextLine();
                battleGUI();
            }
            p++;

        }
    }

    public void efSwing() {
        if (stamina >= 10) {
            System.out.println("You swing your sword at lightning speed, slicing through your opponent!");

            double temp = opphp;

            newDmg = dmg * 1.7;

            if (mightFlag) {
                double tempDmg = mStat + newDmg * 2;
                shield = shield - tempDmg;
            } else shield = shield - newDmg;
            if (shield < 0)
                opphp = opphp + shield;

            double temp2 = temp - opphp;

            System.out.println("Dealt " + temp2 + " damage!");
            System.out.println("Used 10 stamina.");
            stamina = stamina - 10;
        }
    }

    public void dragon(){
        System.out.println("The lair is full of bones.");
        System.out.println("You carefully make your way through.");
        System.out.println("The dragon is sleeping in the center of the den.");
        System.out.println("You prepare a light ball, and aim it at the dragon's snout.");
        System.out.println("It explodes, waking the dragon up.");
        System.out.println("Get ready!");
        opphp = 1500;
        oppDmg = 45;
        n = 2;
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        battleGUI();
        System.out.println("You have slain the dragon.");
        System.out.println("You make your way back to the kingdom.");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
    }

    public void finale(){
        System.out.println("\nToltn: Welcome back, so the dragon has been dealt with?");
        System.out.println("Thank you for your hard work, I'll let the king know.");
        System.out.println("We shall hold a celebration of this occassion.");
        System.out.println("You should join us, Taiwyc.");
        System.out.println("\nYou came here to study magic, and ended up fighting a dragon.");
        System.out.println("Now that you are done with your quest, what else could you do?");
        System.out.println("In the next few weeks, as news spreads and people return, a celebration will be held.");
        System.out.println("But before that, the kingdom needs to be rebuilt from the damage the monsters have done.");
        System.out.println("There's also more magic to study.");
        System.out.println("It seems your quest isn't over after all.");
        System.out.println("\nEnter anything to continue.");
        player.nextLine();
        System.out.println("Thank you for playing A Knight's Quest.");
    }

}