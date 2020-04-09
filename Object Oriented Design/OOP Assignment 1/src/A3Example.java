/**
 * Author: S. Smith
 * Revised: Feb 24, 2020
 * 
 * Description: A3 Examples
 */
package src;
import java.util.ArrayList;
import java.util.Arrays;

import src.LuT;
import src.PointT;
import src.LanduseMapT;
import src.DemT;

public class A3Example
{
   public static void main(String[] args) {
       
      PointT pzero = new PointT(0, 0);
      PointT pnew = pzero.translate(10, 20);
      PointT ptest = new PointT(10, 20);

      ArrayList<ArrayList<LuT>> map = new ArrayList<ArrayList<LuT>>();
      map.add(new ArrayList<LuT>(Arrays.asList(LuT.R, LuT.C, LuT.R)));
      map.add(new ArrayList<LuT>(Arrays.asList(LuT.T, LuT.A, LuT.A)));
      LanduseMapT LuMap = new LanduseMapT(map, 1.0);

      System.out.format("Number of rows equals %d \n", LuMap.getNumRow());
      System.out.format("Number of cols equals %d \n", LuMap.getNumCol());
      System.out.format("Scale of each side of each cell equals %f \n", LuMap.getScale());
      System.out.format("LuMap.get(pzero) == LuT.R equals %b \n", LuMap.get(pzero) == LuT.R);
      LuMap.set(pzero, LuT.A);
      System.out.format("Count of LuT.A %d \n", LuMap.count(LuT.A));

      ArrayList<ArrayList<Integer>> d = new ArrayList<ArrayList<Integer>>();
      d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
      d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3)));
      d.add(new ArrayList<Integer>(Arrays.asList(-1, 3, 5, 4)));
      d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 6, 2)));
      DemT dem = new DemT(d, 1.0);

      System.out.format("Total for dem example equals %d \n", dem.total());
      
      System.out.println("Exercising interfaces complete");
  }
}

