import java.util.Arrays;

public class No88MergeTwoOrderedArrays {
    /**
     * 题目:
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     *
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     *
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
     * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
     * 示例 2：
     *
     * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
     * 输出：[1]
     * 解释：需要合并 [1] 和 [] 。
     * 合并结果是 [1] 。
     * 示例 3：
     *
     * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
     * 输出：[1]
     * 解释：需要合并的数组是 [] 和 [1] 。
     * 合并结果是 [1] 。
     * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
     *
     *
     * 提示：
     *
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[j] <= 109
     *
     *
     * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
     */
    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,2,3,0,0,0};
//        int m = 3;
//        int[] nums2 = new int[]{2,5,6};
//        int n = 3;
//        int[] nums1 = new int[]{0};
//        int m = 0;
//        int[] nums2 = new int[]{1};
//        int n = 1;
        int[] nums1 = new int[]{4,5,6,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{1,2,3};
        int n = 3;
        merge1(nums1, m, nums2, n);
        System.out.print("nums1 = " );
        for (int i : nums1) {
            System.out.print(i + " ");
        }
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int pos1 = m - 1, pos2 = n - 1, pos3 = m + n - 1;
        while (pos1 >= 0 && pos2 >= 0) {
            if (nums1[pos1] < nums2[pos2]) {
                nums1[pos3] = nums2[pos2];
                pos2--;
                pos3--;
            } else {
                nums1[pos3] = nums1[pos1];
                pos1--;
                pos3--;
            }
        }
        while (pos2 >= 0 && pos3 >= 0) {
            nums1[pos3] = nums2[pos2];
            pos2--;
            pos3--;
        }
    }

    /**
     * 方式三：逆向双指针
     *  时间复杂度：O(m+n)。 指针移动单调递减，最多移动 m+n 次，因此时间复杂度为 O(m+n)。
     *  空间复杂度：O(1)。 直接对数组 nums1 原地修改，不需要额外空间。
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n;
        while (m > 0 || n > 0) {
            if (m == 0) {
                nums1[i-1] = nums2[n-1];
                n--;
            } else if (n == 0) {
                break;
            } else if(nums1[m-1] < nums2[n-1]) {
                nums1[i-1] = nums2[n-1];
                n--;
            } else {
                nums1[i-1] = nums1[m-1];
                m--;
            }
            i--;
        }
    }

    /**
     * 方法二：双指针
     *  时间复杂度：O(m+n)。 指针移动单调递增，最多移动 m+n 次，因此时间复杂度为 O(m+n)。
     *  空间复杂度：O(m+n)。 需要建立长度为 m+n 的中间数组 sorted。
     */

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3 = new int[m];
        for (int i = 0; i < m ; i++) {
            nums3[i] = nums1[i];
        }
        int i1 = 0 , i2 = 0 , i3 = 0;
        while (m > 0 || n > 0) {
            if (m == 0) {
                nums1[i1] = nums2[i2];
                i2++;
                n--;
            } else if (n == 0) {
                nums1[i1] = nums3[i3];
                i3++;
                m--;
            } else if (nums3[i3] < nums2[i2]) {
                nums1[i1] = nums3[i3];
                i3++;
                m--;
            } else {
                nums1[i1] = nums2[i2];
                i2++;
                n--;
            }
            i1++;
        }
    }

    /**
     * 方法一：直接合并后排序
     *  时间复杂度：O((m+n)log(m+n))。 排序序列长度为 m+n，套用快速排序的时间复杂度即可，平均情况为 O((m+n)log(m+n))。
     *  空间复杂度：O(log(m+n))。 排序序列长度为 m+n，套用快速排序的空间复杂度即可，平均情况为 O(log(m+n))
     */
    public static void merge4(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
