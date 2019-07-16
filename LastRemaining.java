/**
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:
 * 首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
 * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....
 * 直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 */

/*如果只求最后一个报数胜利者的话，我们可以用数学归纳法解决该问题，为了讨      论方便，先把问题稍微改变一下，并不影响原意：

 问题描述：n个人（编号0~(n-1))，从0开始报数，报到(m-1)的退出，剩下的人 继续从0开始报数。求胜利者的编号。

 我们知道第一个人(编号一定是m%n-1) 出列之后，剩下的n-1个人组成了一个新      的约瑟夫环（以编号为k=m%n的人开始）:

        k  k+1  k+2  ... n-2, n-1, 0, 1, 2, ... k-2并且从k开始报0。

现在我们把他们的编号做一下转换：

k     --> 0

k+1   --> 1

k+2   --> 2

...

...

k-2   --> n-2

k-1   --> n-1

变换后就完完全全成为了(n-1)个人报数的子问题，假如我们知道这个子问题的解： 例如x是最终的胜利者，那么根据上面这个表把这个x变回去不刚好就是n个人情 况的解吗？！！变回去的公式很简单，相信大家都可以推出来：x'=(x+k)%n。

令f[i]表示i个人玩游戏报m退出最后胜利者的编号，最后的结果自然是f[n]。

递推公式

f[1]=0;

f[i]=(f[i-1]+m)%i;  (i>1) 
*/

public class LastRemaining{
    public int lastRemaining_Solution(int n, int m){
        if(n == 0 || m == 0){
            return -1;
        }
        int result = 0;
        for(int i = 2; i <= n; i++){
            result = (result + m) % i;
        }
        return result;
    }

    /**
     * 用数组来模拟环，思路还是比较简单，但是各种下标要理清
     */
    public static int findLastNumber(int n, int m){
        if(n < 1 || m < 1){
            return -1;
        }
        int[] array = new int[n];
        int i = -1, step = 0,count = n;
        //跳出循环时将最后一个元素置为了-1
        while(count > 0){
            //指向上一个被删除对象的下一个元素
            i++;
            if(i >= n){
                //模拟环
                i = 0;
            }
            if(array[i] == -1){
                //跳过被删除的对象
                continue;
            }
            step++;
            if(step == m){
                array[i] = -1;
                step = 0;
                count--;
            }
        }
        //跳出循环时的i，即是最后一个被设置为-1的元素，就是要找的那个元素
        return i;
    }
}