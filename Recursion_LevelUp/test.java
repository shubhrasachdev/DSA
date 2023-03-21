import java.util.*;
public class test {
    static int ans = -1;
	public static void getMaxCapital(int idx, int n, List<Integer> lc, List<Integer> t, List<Integer> psf, int csf) {
		if(n == 0 || idx == lc.size()) {
			if(n == 0) {
				System.out.println(psf.toString());
				System.out.println(csf);
				ans = Math.max(csf, ans);
			}
			return;
		} 
		// subsequence calls - to be included or not to be included.
		if(lc.get(idx) >= 0 && csf >= lc.get(idx)) {
			int val = lc.get(idx);
			lc.set(idx, -val); 
			psf.add(idx);
			getMaxCapital(0, n - 1, lc, t, psf, csf - val + t.get(idx)); 
			psf.remove(psf.size() - 1);
			lc.set(idx, -val);
		}
		getMaxCapital(idx + 1, n, lc, t, psf, csf);

	}

    public static int getMaxCapital(int s, int r, int n, List<Integer> lc, List<Integer> t) {
		for(int i = 0; i < t.size(); i++) {
			int rev = t.get(i) * s;
			t.set(i, rev);
		}
		for(int i = 0; i < lc.size(); i++) {
			// start movie = i
			if(lc.get(i) > r) continue; // cannot start with this movie
			System.out.println(i);
			List<Integer> psf = new ArrayList<>();
			getMaxCapital(i, n, lc, t, psf, r);
		}
		return ans;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int s = scn.nextInt();
        int r = scn.nextInt();
        int n = scn.nextInt();
        int m = scn.nextInt();
        List<Integer> lc = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            lc.add(scn.nextInt());
            t.add(scn.nextInt());

        }
		System.out.println(lc.toString());
		System.out.println(t.toString());

        int res = getMaxCapital(s, r, n, lc, t);
        System.out.println(res);

        
    }
    
}
