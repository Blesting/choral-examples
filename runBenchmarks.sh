echo ""
echo "== DiffieHellman =="
mvn exec:java -Dexec.mainClass="benchmarks.diffiehellman.Main" -Dexec.args="5000 target benchmarks diffiehellman"

echo ""
echo "== Mergesort =="
mvn exec:java -Dexec.mainClass="benchmarks.mergesort.Main" -Dexec.args="100 10 target benchmarks mergesort"

echo ""
echo "== Quicksort =="
mvn exec:java -Dexec.mainClass="benchmarks.quicksort.Main" -Dexec.args="2500 1000 target benchmarks quicksort"