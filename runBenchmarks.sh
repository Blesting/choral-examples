echo ""
echo "== SSOWithRetry =="
mvn exec:java -Dexec.mainClass="benchmarks.ssowithretry.Main" -Dexec.args="500 target benchmarks ssowithretry"

echo ""
echo "== Karatsuba =="
mvn exec:java -Dexec.mainClass="benchmarks.karatsuba.Main" -Dexec.args="500 target benchmarks karatsuba"

echo ""
echo "== VitalsStreaming =="
mvn exec:java -Dexec.mainClass="benchmarks.vitalsstreaming.Main" -Dexec.args="3000 50 target benchmarks vitalsstreaming"

echo ""
echo "== SendPackets =="
mvn exec:java -Dexec.mainClass="benchmarks.sendpackets.Main" -Dexec.args="3000 50 target benchmarks sendpackets"

echo ""
echo "== DownloadFile =="
mvn exec:java -Dexec.mainClass="benchmarks.downloadfile.Main" -Dexec.args="400 50 target benchmarks downloadfile"

echo ""
echo "== DiffieHellman =="
mvn exec:java -Dexec.mainClass="benchmarks.diffiehellman.Main" -Dexec.args="50 target benchmarks diffiehellman"

echo ""
echo "== Mergesort =="
mvn exec:java -Dexec.mainClass="benchmarks.mergesort.Main" -Dexec.args="100 50 target benchmarks mergesort"

echo ""
echo "== Quicksort =="
mvn exec:java -Dexec.mainClass="benchmarks.quicksort.Main" -Dexec.args="100 100 target benchmarks quicksort"
