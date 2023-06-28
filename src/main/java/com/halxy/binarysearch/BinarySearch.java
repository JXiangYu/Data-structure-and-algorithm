package com.halxy.binarysearch;

public class BinarySearch {

    //二分查找 - 基础版
    /*
    Params: arr - 待查找的升序数组
            target - 待查找的目标值
    Returns:    找到则返回索引
                找不到返回 -1
     */

    private static int binarySearchBasic(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            if (target == arr[i]) {
                return i;
            } else if (target == arr[j]) {
                return j;
            }
//            int n = (i + j) / 2;
            int n = (i + j) >>> 1;
            if (target == arr[n]) {
                return n;
            } else if (target < arr[n]) {
                j = n - 1;
                i++;
            } else if (target > arr[n]) {
                i = n + 1;
                j--;
            }
        }
        return -1;
    }

    private static int binarySearchBasic1(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int n = (i + j) >>> 1;
            if (target < arr[n]) {
                j = n - 1;
            } else if (target > arr[n]) {
                i = n + 1;
            } else {
                return n;
            }
        }
        return -1;
    }

    /*
    问题1：为什么是 i <= j 意味着区间内有未比较的元素，而不是 i < j?
        i == j 意味着 i，j 它们指向的元素也会参与比较
        i < j 只意味着 m 指向的元素参与比较

    问题2：（i+j）/2 有没有问题?
        有问题! 应该使用 无符号右移 >>> 符号进行计算
        eg: 如果数组长度很大，等于 Integer.MAX_VALUE, 那么在计算中间值的时候
            第一次 ：  n = (0 + Integer.MAX_VALUE) / 2    --- 无问题  1073741823
            第二次 ：  n = (n + Integer.MAX_VALUE) / 2    --- 有问题， -536870913
                    n + Integer.MAX_VALUE 超过了 Java 最大可表示的正整数，-1073741826
                    首位作为了符号位，结果表示为负数，索引为正。
     */


    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 14, 18, 22, 28, 37, 42, 49, 55, 60, 71, 77, 83, 88, 99};
        int target = 67;
        System.out.println(binarySearchBasic(arr, target));
    }
}
