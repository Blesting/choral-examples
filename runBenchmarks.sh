echo ""
echo "== SendPackets =="
mvn exec:java -Dexec.mainClass="benchmarks.sendpackets.Main" -Dexec.args="1000 target benchmarks sendpackets"

echo ""
echo "== DiffieHellman =="
mvn exec:java -Dexec.mainClass="benchmarks.diffiehellman.Main" -Dexec.args="1000 target benchmarks diffiehellman"

echo ""
echo "== Mergesort =="
mvn exec:java -Dexec.mainClass="benchmarks.mergesort.Main" -Dexec.args="100 10 target benchmarks mergesort"

echo ""
echo "== Quicksort =="
mvn exec:java -Dexec.mainClass="benchmarks.quicksort.Main" -Dexec.args="100 100 target benchmarks quicksort"