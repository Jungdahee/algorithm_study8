package S10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_ACMCraft {

	// Class for linked graph
	static class Depend {
		Building building;
		Depend next;

		public Depend(Building building) {
			this.building = building;
		}
	}

	// Building object
	static class Building {
		int cost;
		int memory;

		Depend depends;

		public Building(int cost) {
			this.cost = cost;
			memory = -1;
		}

		// Add the building need to build this building
		public void add(Building b) {
			Depend d = new Depend(b);
			d.next = depends;
			depends = d;
		}

		// Calculate the time to build
		public void calc() {
			// Don't need other buildings to build this
			// then build time is cost
			if (depends == null) {
				memory = cost;
				return;
			}

			// For all depends
			Depend d = depends;
			while (d != null) {
				Building b = d.building;
				// If not calculated yet
				if (b.memory == -1)
					b.calc();

				// Find max value
				memory = Math.max(memory, cost + b.memory);

				d = d.next;
			}

			return;
		}
	}

	private static int N, K, W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			Building[] buildings = new Building[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				buildings[i] = new Building(Integer.parseInt(st.nextToken()));
			}

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				int b1 = Integer.parseInt(st.nextToken()) - 1;
				int b2 = Integer.parseInt(st.nextToken()) - 1;

				// Need building b1 to build b2
				buildings[b2].add(buildings[b1]);
			}

			W = Integer.parseInt(br.readLine()) - 1;

			// End of input
			
			// Calculate build time of W building
			buildings[W].calc();

			// Write result
			sb.append(buildings[W].memory).append("\n");
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

}
