/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galeshapley;

import java.util.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class GaleShapley *
 */
public class GaleShapley {

    private ArrayList<String> hospitals = new ArrayList<String>();
    private ArrayList<String> residents = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> hosPref = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ArrayList<String>> resPref = new HashMap<String, ArrayList<String>>();
    private HashMap<String, Integer> hosSlots = new HashMap<String, Integer>();
    private int totalSlots = 0;
    private static int totalSlotsFilled = 0;
    private HashMap<String, ArrayList<String>> paired = new HashMap<String, ArrayList<String>>();
    private HashMap<String, String> hosPairedSlots = new HashMap<String, String>();
    private ArrayList<String> hosResidents = new ArrayList<String>();
    private HashMap<String, Boolean> status = new HashMap<String, Boolean>();

    public GaleShapley(ArrayList<String> hos, ArrayList<String> res, HashMap<String, ArrayList<String>> hPref, HashMap<String, ArrayList<String>> rPref, HashMap<String, Integer> hSlots) {
        hospitals = hos;
        residents = res;
        hosPref = hPref;
        resPref = rPref;
        hosSlots = hSlots;
        for (int i = 0; i < residents.size(); i++) {
            status.put(residents.get(i), Boolean.FALSE);
        }
        for (int i = 0; i < hospitals.size(); i++) {
            paired.put(hospitals.get(i), new ArrayList<String>());
        }
        for (int i = 0; i < hos.size(); i++) {
            totalSlots = totalSlots + hosSlots.get(hospitals.get(i));
        }

    }

    private GaleShapley() {

    }

    public void mapping() {

        while (true) {

            for (int i = 0; i < hospitals.size(); i++) {
                ArrayList<String> dummy = new ArrayList<String>();
                for (int j = 0; j < residents.size(); j++) {
                    if (paired.get(hospitals.get(i)).size() < hosSlots.get(hospitals.get(i))) {

                        String res = hosPref.get(hospitals.get(i)).get(j);

                        if (!status.get(res)) {
                            status.put(res, Boolean.TRUE);
                            dummy.add(res);

                            paired.put(hospitals.get(i), dummy);

                            hosPairedSlots.put(res, hospitals.get(i));

                            totalSlotsFilled++;

                        } else {
                            String x = hosPairedSlots.get(res);
                            int indexOld = resPref.get(res).indexOf(x);
                            int indexNew = resPref.get(res).indexOf(hospitals.get(i));
                            if (indexNew < indexOld) {
                                if (paired.get(x).size() == 1) {
                                    paired.put(x, new ArrayList<String>());
                                } else {
                                    paired.get(x).remove(res);
                                }
                                //int index=paired.get(x).indexOf(res);
                                //paired.get(x).set(index, null);
                                status.put(res, Boolean.FALSE);
                                paired.get(hospitals.get(i)).add(res);
                                status.put(res, Boolean.TRUE);
                                hosPairedSlots.put(res, hospitals.get(i));
                                //totalSlotsFilled++;

                            }

                        }

                    } else {
                        break;
                    }
                }

            }
            
            
            if ((totalSlotsFilled == totalSlots) || ((hosPairedSlots.size() == residents.size()) && (residents.size()<totalSlots))) {
                break;
            } 
        }
    }

    public void printValues() {
        System.out.println("Final Pairs");
        System.out.println();
        System.out.println(hosPairedSlots);
    }

    public static void main(String[] args) {

        System.out.println("enter the no of hospitals");
        Scanner sc = new Scanner(System.in);
        int countOfHospitals = sc.nextInt();
        ArrayList<String> h = new ArrayList<String>();
        ArrayList<String> hDummy = new ArrayList<String>();
        for (int j = 0; j < countOfHospitals; j++) {
            int k = j + 1;
            h.add("h" + k);
            hDummy.add("h" + k);
        }

        System.out.println("enter the no of residents");
        Scanner sc2 = new Scanner(System.in);
        int countOfResidents = sc2.nextInt();
        ArrayList r = new ArrayList();
        ArrayList rDummy = new ArrayList();

        for (int j = 0; j < countOfResidents; j++) {
            int k = j + 1;
            r.add("r" + k);
            rDummy.add("r" + k);
        }

        GaleShapley g = new GaleShapley();
        HashMap<String, ArrayList<String>> hp = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < h.size(); i++) {
            ArrayList rDummy_tmp = new ArrayList();
            rDummy_tmp = rDummy;
            rDummy_tmp = g.shuffleList(rDummy_tmp);

            ArrayList a = new ArrayList();
            for (int m = 0; m < rDummy_tmp.size(); m++) {
                String x = (String) rDummy_tmp.get(m);
                a.add(m, x);

            }

            hp.put(h.get(i), a);
        }

        /**
         * Resident preference *
         */
        HashMap rp = new HashMap();
        for (int i = 0; i < r.size(); i++) {
            ArrayList hDummy_tmp = new ArrayList();
            hDummy_tmp = hDummy;
            hDummy_tmp = g.shuffleList(hDummy_tmp);

            ArrayList a2 = new ArrayList();
            for (int m = 0; m < hDummy_tmp.size(); m++) {
                String x = (String) hDummy_tmp.get(m);
                a2.add(m, x);

            }

            rp.put(r.get(i), a2);
        }

        //randomly create slots for hospitals
        HashMap hospitalSlots = new HashMap();
        Random randomGenerator = new Random();
        for (int idx = 0; idx < countOfHospitals; idx++) {
            hospitalSlots.put(h.get(idx), randomGenerator.nextInt(1) + 1);

        }
        System.out.println("hospital preferences");
        for (int i = 0; i < h.size(); i++) {
            System.out.println(h.get(i) + "(" + hospitalSlots.get(h.get(i)) + ")" + ":" + hp.get(h.get(i)));
        }

        System.out.println("residents preferences");
        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i) + ":" + rp.get(r.get(i)));
        }

        GaleShapley gs = new GaleShapley(h, r, hp, rp, hospitalSlots);
        gs.mapping();
        gs.printValues();
    }

    public ArrayList shuffleList(ArrayList<Integer> a) {

        Random rnd = new Random();
        int seed = rnd.nextInt();
        rnd.setSeed(seed);
        Collections.shuffle(a, rnd);
        return a;
    }

}
