echo ""
echo "== SSOWithRetry =="
mvn exec:java -Dexec.mainClass="benchmarks.ssowithretry.modified.Main" -Dexec.args="1000 target benchmarks ssowithretry modified"

echo ""
echo "== Mergesort =="
mvn exec:java -Dexec.mainClass="benchmarks.mergesort.modified.Main" -Dexec.args="100 1000 target benchmarks mergesort modified"

echo ""
echo "== Quicksort =="
mvn exec:java -Dexec.mainClass="benchmarks.quicksort.modified.Main" -Dexec.args="800 1000 target benchmarks quicksort modified"

echo ""
echo "== Karatsuba =="
mvn exec:java -Dexec.mainClass="benchmarks.karatsuba.modified.Main" -Dexec.args="1000 target benchmarks karatsuba modified"

