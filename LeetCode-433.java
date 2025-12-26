// 433. Minimum Genetic Mutation
// https://leetcode.com/problems/minimum-genetic-mutation
// optimized
class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
		// try all combinations of mutations
		// check if exists in bank or not.
		// if exists count no. of mutation
		Set<String> set = new HashSet<>(bank.length);
		for(String b : bank){
			set.add(b);
		}
        if(!set.contains(endGene))
			return -1;
        int count = 0;
		Queue<String> q = new ArrayDeque<>();
		q.add(startGene);
        set.remove(startGene);
		char[] geneS = {'A','C','G','T'};
		while(!q.isEmpty()){
            int size = q.size();
            for(int s=0;s<size;s++){
                String currentGene = q.poll();
                if(currentGene.equals(endGene))
                    return count;
				char[] currGene = currentGene.toCharArray();
                for(int c=0;c<currGene.length;c++){
				    char original = currGene[c];
                    for(int i=0;i<geneS.length;i++){
                        if(geneS[i] != currGene[c]){
							currGene[c] = geneS[i];
                            String newGene = new String(currGene);
                            if(set.contains(newGene)){
                                q.add(newGene);
                                set.remove(newGene);
                            }
							currGene[c] = original;
                        }
                    }
                }
            }
            count++;
		}
		return -1;
    }
}

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
		// try all combinations of mutations
		// check if exists in bank or not.
		// if exists count no. of mutation
		Set<String> set = new HashSet<>();
		for(String b : bank){
			set.add(b);
		}
		int count = 0;
        if(!set.contains(endGene))
			    return -1;
		Queue<String> q = new ArrayDeque<>();
		q.add(startGene);
        set.remove(startGene);
		while(!q.isEmpty()){
            int size = q.size();
            for(int s=0;s<size;s++){
                String currentGene = q.poll();
                if(currentGene.equals(endGene))
                    return count;
                String geneS = "ACGT";
                int len = currentGene.length();
                for(int i=0;i<geneS.length();i++){
                    char ch = geneS.charAt(i);
                    for(int c=0;c<len;c++){
                        if(ch != currentGene.charAt(c)){
                            String newGene = currentGene.substring(0, c) + ch + currentGene.substring(c+1);
                            if(set.contains(newGene)){
                                q.add(newGene);
                                set.remove(newGene);
                            }
                        }
                    }
                }
            }
            count++;
		}
		return -1;
    }
}