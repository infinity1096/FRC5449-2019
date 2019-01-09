/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FileReadWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProfileWriter implements Runnable{
    String address = "home/lvuser/frc/Profile.csv";
    PrintWriter pw;
    File file = new File(address);
    double[][] profiles;
    int count;

    public ProfileWriter(){}

    public void loadBuffer(double[][] buffer,int count){
        this.profiles = buffer.clone();
        this.count = count;
    }


    public void SetAddress(String address) {
        this.address = address;
        this.file = new File(address);
    }

    @Override
    public void run() {
        try {
            pw = new PrintWriter(file);
            StringBuilder builder = new StringBuilder();
            String ColumnNamesList = "Position,Velocity,duration";
            // No need give the headers Like: id, Name on builder.append
            pw.println(ColumnNamesList);
            pw.flush();
            for (int i = 0; i < count; ++i){
                pw.println(String.valueOf(this.profiles[i][0]) + "," + String.valueOf(this.profiles[i][1]) + "," + String.valueOf(this.profiles[i][2]));
                pw.flush();
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}



    


  

