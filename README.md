# AlgorithmProblem
算法与数据结构

## # 动态规划

### 概念与解释

首先明确：动态规划是用来求解最优化问题的一种方法。常规算法书上强调的是无后效性和最优子结构描述，这套理论是正确的，但是适用与否与你的状态表述有关。至于划分阶段什么的就有些扯淡了：动态规划不一定有所谓的阶段。其实质是状态空间的状态转移。

所有的决策类求最优解的问题都是在状态空间内找一个可以到达的最佳状态。搜索的方式是去遍历每一个点，而动态规划则是把状态空间变形，由此变成从初始到目标状态的最短路问题。

依照这种描述：假若你的问题的结论包含若干决策，则可以认为从初始状态（边界条件）到解中间的决策流程是一个决策状态空间中的转移路线。前提是：你的**状态描述可以完整且唯一地覆盖所有有效的状态空间中的点，且转移路线包含所有可能的路径**。

这个描述是包含动态规划两大条件的。所谓无后效性，指状态间的转移与如何到达某状态无关。如果有关，意味着你的状态描述不能完整而唯一地包括每一个状态。如果你发现一个状态转移有后效性，很简单，**把会引起后效性的参数作为状态描述的一部分放进去将其区分开来就可以了**；最优子结构说明转移路线包含了所有可能的路径，如果不具备最优子结构，意味着有部分情况没有在转移中充分体现，**增加转移的描述**就可以了。最终所有的搜索问题都可以描述成状态空间内的状态转移方程，只是有可能状态数量是指数阶的，有可能不满足计算要求罢了。

这样的描述下，所有的动态规划问题都可以转变为状态空间内大量可行状态点和有效转移构成的图的从初始状态到最终状态的最短路问题。

于是乎，对于动态规划，他的本质就是图论中的最短路；阶段可以去除，因为不一定有明确的阶段划分。

**动态规划的本质是分治思想和解决冗余，关键是找到刻画问题本质的状态定义以及状态转移。**

### 定义状态常用技巧与方法

#### 解题顺序

1. 转化、预处理问题、对问题变形（如排序）
2. 确定状态
3. 确定状态转移
4. 初始条件和边界
5. 计算顺序（递归或迭代）

#### 常用技巧

- 逆向思维（倒推法），从目标状态出发倒推回初始状态或边界状态，如果原问题可以分解成几个本质上相同、规模更小的问题；假设已经找到问题的解，反过来推导子问题；考察**最后一步**，然后看能否转化为子问题；为什么考察最后一步能够得出状态转移方程？**因为如果把动态规划转移过程看出有向无环图的话，目标节点只和它依赖的前驱节点相关，而考察最后一步有助于找到目标节点与前驱节点的转移关系**。
- 考察问题的变量和约束条件，题目中有变量或约束条件时，往往需要将变量或约束条件加入状态定义中
- 状态定义尽可能简单，只有在状态定义不满足无后效性或不能完整的刻画问题时，才考虑**增加状态的维度**
- 状态可能是一个数（数位dp）、一个集合（状态压缩dp）、一维变量、多维变量甚至一个向量
- 状态定义和问题本身密切相关，合适的状态需要能完全覆盖问题的某个“阶段”，只要状态一致，具体经过怎样的步骤到达这一状态是没有影响的，一般状态是根据演变过程的特点寻找。
- 状态转移既可以由过去推出现在，也可以有未来状态推出现在状态（接苹果），即正向推导或逆向推导
- 有些问题需要对原问题先做处理、转化后，才能使用动态规划
- 确定问题的子问题； 要点：注意分析哪些量随着问题规模的变小是会变小的，哪些变量
  与问题无关。确定状态要点：结合子问题，敢想敢试，不要轻易否定一个状态，多思考，不要希望
  每个题都能一蹴而就 
- 尝试几个简单例子，看能不能找到规律、以及合适的问题划分方式

#### 常用方法

- 模型匹配法：
  最先考虑的就是这个方法了。挖掘问题的本质，如果发现问题是自己熟悉的某个基本的模型，就
  直接套用，但要小心其中的一些小的变动，现在考题办都是基本模型的变形套用时要小心条件，三思
  而后行。

- 三要素法
  仔细分析问题尝试着确定动态规划的三要素，不同问题的却定方向不同：
  先确定阶段的问题：数塔问题
  先确定状态的问题：大多数都先确定状态的。
  先确定决策的问题：背包问题
  一般都是先从比较明显的地方入手，至于怎么知道哪个明显就是经验问题了，多做题就会发现。
  寻找规律法：
  这个方法很简单，耐心推几组数据后，看他们的规律，总结规律间的共性，有点贪心的意思。

- 边界条件法
  找到问题的边界条件，然后考虑边界条件与它的领接状态之间的关系。这个方法也很起效。

- 放宽约束和增加约束
  这个思想是在陈启锋的论文里看到的，具体内容就是给问题增加一些条件或删除一些条件使问题
  变的清晰。

### 常见的动态规划类型

- 坐标型
- 序列型（双序列）
- 区间型
- 划分型
- 背包型
- 博弈型
- 状态压缩型
- 树型
- 数位型
- 概率型

### 动态规划能够解决问题类型

- 计数
- 存在性判定
- 最值



例题1：最长上升子序列

a1 a2 a3 ...       ai

假设我们定义 f[i] 为前 i 个元素的最长上升子序列个数，在考察 f[i+1] 时，我们是没法从 f[j] |j<i+1 推导的，因为我们不知道 a[i+1] 能否加入 f[i] ，为了能够判断新的元素如 a[i+1] 能否加入前面已经算过的子序列中，必须记录子序列的**结尾元素**，这也是由问题本身决定的（上升子序列），故定义 f[i] 为 a[i] 结尾的最长上升子序列，考察 f[i+1] 就简单了，只要根据 a[i+1] 与 a[i] 的大小，便能找到 f[i+1] 与 f[i] 的关系，不过稍加思考便知，f[i+1] 不仅仅与 f[i] 有关，也可能与 f[i-1] f[i-2] ... f[1] 有关，故取其最大值即可。

  

  

  



## 参考文档

http://praveendhinwacoding.blogspot.com/2013/06/700-problems-to-understand-you-complete.html

https://www.quora.com/What-are-some-of-the-best-books-with-which-to-learn-dynamic-programming **DP**

http://bestprogrammingbooks.com/7-dynamic-programming-books-recommended-stackoverflow/ **DP** 

https://stackoverflow.com/questions/4278188/good-examples-articles-books-for-understanding-dynamic-programming **DP** 

http://www.cnblogs.com/zhuli19901106/p/thu-cs-kaoyan.html#3926959

https://github.com/jwasham/coding-interview-university/blob/master/translations/README-cn.md

https://github.com/jwasham/coding-interview-university/blob/master/translations/README-cn.md

 https://www.geeksforgeeks.org/dynamic-programming/ **动态规划**

http://blog.refdash.com/dynamic-programming-tutorial-example/

https://a2oj.com/category?ID=33

 [**https://github.com/mission-peace/interview**](https://github.com/mission-peace/interview) **面试**

[**https://github.com/yangshun/tech-interview-handbook**](https://github.com/yangshun/tech-interview-handbook)

[**https://www.youtube.com/user/tusharroy2525/featured**](https://www.youtube.com/user/tusharroy2525/featured)

https://www.tutorialspoint.com/design_and_analysis_of_algorithms/design_and_analysis_of_algorithms_dynamic_programming.htm **DP** 

https://medium.com/@kingrayhan/500-data-structures-and-algorithms-practice-problems-and-their-solutions-b45a83d803f0

http://www.techiedelight.com/Tags/guava/

https://www.techgig.com/practice/question/RGFQeXdxV1orQzBxVGw2Q1RVOWhtZz09

https://www.careercup.com/book

 [**https://www.quora.com/How-can-one-start-solving-dynamic-programming-problems**](https://www.quora.com/How-can-one-start-solving-dynamic-programming-problems)  **DP**

[**https://medium.freecodecamp.org/demystifying-dynamic-programming-3efafb8d4296**](https://medium.freecodecamp.org/demystifying-dynamic-programming-3efafb8d4296)

[**https://www.hackerrank.com/topics/dynamic-programming**](https://www.hackerrank.com/topics/dynamic-programming)

[**https://blog.pramp.com/how-to-solve-any-dynamic-programming-problem-603b6fbbd771**](https://blog.pramp.com/how-to-solve-any-dynamic-programming-problem-603b6fbbd771)

[**https://coderworld109.blogspot.com/2018/01/how-to-solve-dynamic-programming-problem.html**](https://coderworld109.blogspot.com/2018/01/how-to-solve-dynamic-programming-problem.html)

[**http://www.techiedelight.com/introduction-dynamic-programming/**](http://www.techiedelight.com/introduction-dynamic-programming/)

https://www.youtube.com/watch?v=tb_14w_-mNw

https://www.byte-by-byte.com/fast-method/

https://www.codementor.io/rishabhdaal/dynamic-programming-lifeline-of-technical-interviews-hx0txgu8j

http://blog.gaurav.im/2017/08/16/dynamic-programming-you-can-do-it-half-asleep/

https://www.quora.com/What-are-systematic-ways-to-prepare-for-dynamic-programming

https://www.quora.com/What-is-the-best-way-to-learn-dynamic-programming

http://www.8bitavenue.com/2011/11/introduction-to-dynamic-programming/

http://blog.gainlo.co/index.php/2015/10/22/a-step-by-step-guide-to-dynamic-programming/?utm_campaign=quora&utm_medium=What+are+systematic+ways+to+prepare+for+dynamic+programming%3F&utm_source=quora

https://www.quora.com/Are-there-any-good-resources-or-tutorials-for-dynamic-programming-DP-besides-the-TopCoder-tutorial

https://www.quora.com/How-do-I-understand-recursion-in-deep-and-all-related-field-backtracking-dynamic-programming-and-so-on

https://www.quora.com/What-is-backtracking-in-algorithms

https://www.quora.com/What-common-problems-are-solved-with-dynamic-programming

https://www.quora.com/Whats-the-difference-between-greedy-algorithm-and-dynamic-programming-Is-a-greedy-program-a-subset-of-dynamic-programming

https://web.stanford.edu/class/cs97si/

https://www.coursera.org/learn/algorithmic-toolbox#pricing

 http://blog.greenwicher.com/2017/01/03/codeforces-problemset/ **codeforce使用** 

 https://www.youtube.com/channel/UCJjC1hn78yZqTf0vdTC6wAQ **视频教材** 

 https://blog.csdn.net/zuzhiang/article/details/78134247 单调栈

https://blog.csdn.net/liujian20150808/article/details/50752861

https://blog.csdn.net/A1847225889/article/details/77777009

https://blog.csdn.net/yutianzuijin/article/details/52072427

https://leetcode.com/problems/wildcard-matching/discuss/138878/Finite-state-machine-with-Python-and-dictionary.-13-lines-O(s+p)-time

https://mp.weixin.qq.com/s/M9QY-Oe9xdLW2plFNlraFw

 http://www.cnblogs.com/wuyuegb2312/p/3273337.html       回溯

https://www.cnblogs.com/wuyuegb2312/p/3281264.html    DP 

https://www.kancloud.cn/kancloud/pack/70135

https://www.zhihu.com/question/39948290/answer/155958549

https://www.zhihu.com/question/23995189

https://zhuanlan.zhihu.com/p/33574315

https://www.zhihu.com/question/23995189

https://blog.csdn.net/arbuckle/article/details/710988

https://www.kancloud.cn/kancloud/pack/70124

http://lucida.me/blog/on-learning-algorithms/

https://mp.weixin.qq.com/s/uGx-rq0-P1xxgt4CVW7pVg

https://mp.weixin.qq.com/s/emEuhftLbAQmdn1LQYOSBw
