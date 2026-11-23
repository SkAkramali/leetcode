class Solution {
    public List<String> findAllRecipes(
        String[] recipes,
        List<List<String>> ingredients,
        String[] supplies
    ) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        // Build the graph
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];

            indegree.put(recipe, ingredients.get(i).size());

            for (String ingredient : ingredients.get(i)) {
                graph.computeIfAbsent(ingredient, k -> new ArrayList<>())
                     .add(recipe);
            }
        }

        // Initialize queue with available supplies
        Queue<String> queue = new LinkedList<>();

        for (String supply : supplies) {
            queue.offer(supply);
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String item = queue.poll();

            if (!graph.containsKey(item)) {
                continue;
            }

            for (String recipe : graph.get(item)) {
                indegree.put(recipe, indegree.get(recipe) - 1);

                if (indegree.get(recipe) == 0) {
                    result.add(recipe);
                    queue.offer(recipe);
                }
            }
        }

        return result;
    }
}