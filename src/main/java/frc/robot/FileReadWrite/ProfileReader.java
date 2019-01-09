/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FileReadWrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Add your docs here.
 */
public class ProfileReader implements Runnable{
    private String address;
    private boolean is_read = false;
    private int count = 0;
    private double[][] profile;
    public ProfileReader(){}
    public void Setaddress(String address){
        this.address = address;
    }

    public boolean is_finished(){
        return this.is_read;
    }

    private int getnumrows(){
        File file = new File(this.address);
        int ros = 0;
        try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter(",");
            while(sc.hasNextLine()){
                sc.nextLine();
                ros ++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ros;
    }

    public double[][] getArray(){
        return this.profile.clone();
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        is_read = false;
        File file = new File(this.address);
        int rows = getnumrows();
        double[][] array = new double[rows-1][3];
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            for (int i = 0; i < rows-1;i++){
                String input = sc.nextLine();
                String[] inarray = input.split(",");
                for (int j = 0;j < 3;j++){  
                array[i][j] = Double.parseDouble(inarray[j]);
                }
            }
            this.count = rows -1;
            this.profile = array;
            is_read = true;
        }catch(FileNotFoundException e){

        }
    }


}
