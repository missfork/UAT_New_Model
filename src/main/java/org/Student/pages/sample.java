package org.Student.pages;

import org.testng.annotations.Test;

public class sample {





        public int[] twoSum(int[] nums, int target) {


            for (int  i =0; i< nums.length;i++){
                System.out.println("check :"+i)  ;
                for(int j=0;j<nums.length;j++){

                    if (nums[i]==target-nums[j]){
                        return new int[] { i, j };
                    }

                }

            }
            return null;
        }

    //nums = [2,7,11,15], target = 9
    @Test
  public void main(){
      int[] n = {2,7,11,15,30,40};

        for (int element:twoSum(n,26)) {
            System.out.println(element);
        }

  }
}
