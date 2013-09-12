package org.example.quicksort

class QuickSortApp {

    static void main(String[] args) {
        def arraySize = args.size() == 1 ? args[0] as int : 10000
        def origNumbers = generateRandomNumbers(arraySize)

        // Java
        def numbers = origNumbers as int[]

        profile("Java") {
            JavaQuickSort.quickSort(numbers)
            println numbers[0..<20]
        }

        // Groovy
        numbers = origNumbers as int[]
        profile("Groovy") {
            GroovyQuickSort.quickSort(numbers)
            println numbers[0..<20]
        }

        // Groovy CompileStatic
        numbers = origNumbers as int[]
        profile("GroovyCS") {
            GroovyCSQuickSort.quickSort(numbers)
            println numbers[0..<20]
        }
    }

    private static List<Integer> generateRandomNumbers(int length) {
        List<Integer> result = new ArrayList(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(length));
        }

        return result;
    }

    private static void profile(String name, Closure c) {
        def start = System.currentTimeMillis()
        c.call()
        println "Time taken for $name: ${System.currentTimeMillis() - start}ms"
    }
}
