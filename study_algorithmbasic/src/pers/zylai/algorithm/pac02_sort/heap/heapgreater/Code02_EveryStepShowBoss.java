package pers.zylai.algorithm.pac02_sort.heap.heapgreater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code02_EveryStepShowBoss {

	public static class Customer {
		//用户id
		public int id;
		//购买的商品数目
		public int buy;
		//进入某个区域的时间
		public int enterTime;

		public Customer(int v, int b, int o) {
			id = v;
			buy = b;
			enterTime = 0;
		}
	}

	public static class CandidateComparator implements Comparator<Customer> {

		@Override
		public int compare(Customer o1, Customer o2) {
			return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
		}

	}

	public static class DaddyComparator implements Comparator<Customer> {

		@Override
		public int compare(Customer o1, Customer o2) {
			return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
		}

	}

	public static class WhosYourDaddy {
		private HashMap<Integer, Customer> customers;
		//加强堆
		private HeapGreater<Customer> candHeap;
		//加强堆
		private HeapGreater<Customer> daddyHeap;
		//得奖区大小
		private final int daddyLimit;

		public WhosYourDaddy(int limit) {
			customers = new HashMap<Integer, Customer>();
			//传入比较器，谁购买数最多且时间最早排在上面
			candHeap = new HeapGreater<>(new CandidateComparator());
			//谁购买数最小且时间最早排在上面
			daddyHeap = new HeapGreater<>(new DaddyComparator());
			daddyLimit = limit;
		}

		// 当前处理i号事件，arr[i] -> id,  buyOrRefund
		public void operate(int time, int id, boolean buyOrRefund) {
			//如果时候退货且区里都没有你,就当做没发生
			if (!buyOrRefund && !customers.containsKey(id)) {
				return;
			}
			//如果用户表中没有这个id，就放进去
			if (!customers.containsKey(id)) {
				customers.put(id, new Customer(id, 0, 0));
			}
			Customer c = customers.get(id);
			if (buyOrRefund) {
				c.buy++;
			} else {
				c.buy--;
			}
			if (c.buy == 0) {
				customers.remove(id);
			}
			if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
				if (daddyHeap.size() < daddyLimit) {
					c.enterTime = time;
					daddyHeap.push(c);
				} else {
					c.enterTime = time;
					candHeap.push(c);
				}
			} else if (candHeap.contains(c)) {
				//如果购买数为0,移除他,复杂度logN
				if (c.buy == 0) {
					candHeap.remove(c);
				} else {
					//否则,因为购买数变了,调整堆,logN
					candHeap.resign(c);
				}
			} else {
				if (c.buy == 0) {
					daddyHeap.remove(c);
				} else {
					daddyHeap.resign(c);
				}
			}
			daddyMove(time);
		}

		public List<Integer> getDaddies() {
			List<Customer> customers = daddyHeap.getAllElements();
			List<Integer> ans = new ArrayList<>();
			for (Customer c : customers) {
				ans.add(c.id);
			}
			return ans;
		}

		private void daddyMove(int time) {
			if (candHeap.isEmpty()) {
				return;
			}
			if (daddyHeap.size() < daddyLimit) {
				Customer p = candHeap.pop();
				p.enterTime = time;
				daddyHeap.push(p);
			} else {
				//和暴力的方法都一样,只是这里使用了效率更高的方法
				if (candHeap.peek().buy > daddyHeap.peek().buy) {
					Customer oldDaddy = daddyHeap.pop();
					Customer newDaddy = candHeap.pop();
					oldDaddy.enterTime = time;
					newDaddy.enterTime = time;
					daddyHeap.push(newDaddy);
					candHeap.push(oldDaddy);
				}
			}
		}

	}

	//优化之后的主方法
	public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		//大的结构
		WhosYourDaddy whoDaddies = new WhosYourDaddy(k);
		//每一步返回一个答案就行
		for (int i = 0; i < arr.length; i++) {
			whoDaddies.operate(i, arr[i], op[i]);
			ans.add(whoDaddies.getDaddies());
		}
		return ans;
	}

	// 干完所有的事，模拟，不优化
	public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
		//id对应的实例
		HashMap<Integer, Customer> map = new HashMap<>();
		//候选区的数组
		ArrayList<Customer> cands = new ArrayList<>();
		//得奖区的数组
		ArrayList<Customer> daddy = new ArrayList<>();
		//每一个时间点的答案，每一个答案是一个数组
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int id = arr[i];
			boolean buyOrRefund = op[i];
			//如果该用户退货，且不在map中。即
			if (!buyOrRefund && !map.containsKey(id)) {
				ans.add(getCurAns(daddy));
				continue;
			}
			// 没有发生：用户购买数为0并且又退货了，这个在上面判断过了
			// 用户之前购买数是0，此时买货事件，那么要么卖货要么退货
			// 用户之前购买数>0， 此时买货
			// 用户之前购买数>0, 此时退货
			if (!map.containsKey(id)) {
				//这里先直接放进入，在之后具体进入哪个区的时候再添加时间
				map.put(id, new Customer(id, 0, 0));
			}
			// 买、卖
			Customer c = map.get(id);
			if (buyOrRefund) {
				c.buy++;
			} else {
				c.buy--;
			}
			if (c.buy == 0) {
				map.remove(id);
			}
			// c
			// 下面做
			/*如果既不在候选区也不在得奖区，说明是初来乍到*/
			if (!cands.contains(c) && !daddy.contains(c)) {
				//如果得奖区没有满，就加入得奖区
				if (daddy.size() < k) {
					c.enterTime = i;
					daddy.add(c);
				} else {//否则加入候选区
					c.enterTime = i;
					cands.add(c);
				}
			}
			//删除两个区里面购买数为0的
			cleanZeroBuy(cands);
			cleanZeroBuy(daddy);
			//将候选区排序，规则是最好的放在前面，即购买数大的和时间早的放前面
			cands.sort(new CandidateComparator());
			//得奖区排序，规则是最差的放前面，即购买数最小的和时间最早的放前面
			daddy.sort(new DaddyComparator());
			//看看能不能移动
			move(cands, daddy, k, i);
			//将答案放到集合
			ans.add(getCurAns(daddy));
		}
		return ans;
	}

	public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time) {
		//候选区是空的，不考虑后面的问题
		if (cands.isEmpty()) {
			return;
		}
		// 候选区不为空
		//得奖区没满，就是可能得奖区最差的那一个退货之后为0，就直接删除了
		if (daddy.size() < k) {
			Customer c = cands.get(0);
			//重置时间点
			c.enterTime = time;
			daddy.add(c);
			cands.remove(0);
		} else { // 等奖区满了，候选区有东西
			//如果候选区最厉害的那个购买数大于得奖区中最差的那个的购买数
			if (cands.get(0).buy > daddy.get(0).buy) {
				Customer oldDaddy = daddy.get(0);
				daddy.remove(0);
				Customer newDaddy = cands.get(0);
				cands.remove(0);
				newDaddy.enterTime = time;
				oldDaddy.enterTime = time;
				daddy.add(newDaddy);
				cands.add(oldDaddy);
			}
		}
	}

	public static void cleanZeroBuy(ArrayList<Customer> arr) {
		List<Customer> noZero = new ArrayList<Customer>();
		for (Customer c : arr) {
			if (c.buy != 0) {
				noZero.add(c);
			}
		}
		arr.clear();
		for (Customer c : noZero) {
			arr.add(c);
		}
	}

	public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
		List<Integer> ans = new ArrayList<>();
		for (Customer c : daddy) {
			ans.add(c.id);
		}
		return ans;
	}

	// 为了测试
	public static class Data {
		public int[] arr;
		public boolean[] op;

		public Data(int[] a, boolean[] o) {
			arr = a;
			op = o;
		}
	}

	// 为了测试
	public static Data randomData(int maxValue, int maxLen) {
		int len = (int) (Math.random() * maxLen) + 1;
		int[] arr = new int[len];
		boolean[] op = new boolean[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * maxValue);
			op[i] = Math.random() < 0.5 ? true : false;
		}
		return new Data(arr, op);
	}

	// 为了测试
	public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
		if (ans1.size() != ans2.size()) {
			return false;
		}
		for (int i = 0; i < ans1.size(); i++) {
			List<Integer> cur1 = ans1.get(i);
			List<Integer> cur2 = ans2.get(i);
			if (cur1.size() != cur2.size()) {
				return false;
			}
			cur1.sort((a, b) -> a - b);
			cur2.sort((a, b) -> a - b);
			for (int j = 0; j < cur1.size(); j++) {
				if (!cur1.get(j).equals(cur2.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int maxValue = 10;
		int maxLen = 100;
		int maxK = 6;
		int testTimes = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			Data testData = randomData(maxValue, maxLen);
			int k = (int) (Math.random() * maxK) + 1;
			int[] arr = testData.arr;
			boolean[] op = testData.op;
			List<List<Integer>> ans1 = topK(arr, op, k);
			List<List<Integer>> ans2 = compare(arr, op, k);
			if (!sameAnswer(ans1, ans2)) {
				for (int j = 0; j < arr.length; j++) {
					System.out.println(arr[j] + " , " + op[j]);
				}
				System.out.println(k);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("出错了！");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
