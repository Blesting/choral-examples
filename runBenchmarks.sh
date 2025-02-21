echo ""
echo "== DownloadFile =="
mvn exec:java -Dexec.mainClass="benchmarks.downloadfile.Main" -Dexec.args="400 50 target benchmarks downloadfile"

echo ""
echo "== DiffieHellman =="
mvn exec:java -Dexec.mainClass="benchmarks.diffiehellman.Main" -Dexec.args="500 target benchmarks diffiehellman"

echo ""
echo "== ConsumeItems =="
mvn exec:java -Dexec.mainClass="benchmarks.consumeitems.Main" -Dexec.args="3000 1000 target benchmarks consumeitems"

echo ""
echo "== SplitAndCombine =="
mvn exec:java -Dexec.mainClass="benchmarks.splitandcombine.Main" -Dexec.args="1000 target benchmarks splitandcombine"

echo ""
echo "== Quicksort =="
mvn exec:java -Dexec.mainClass="benchmarks.quicksort.Main" -Dexec.args="100 1000 target benchmarks quicksort"

echo ""
echo "== SSOWithRetry =="
mvn exec:java -Dexec.mainClass="benchmarks.ssowithretry.Main" -Dexec.args="5000 target benchmarks ssowithretry"

echo ""
echo "== Karatsuba =="
mvn exec:java -Dexec.mainClass="benchmarks.karatsuba.Main" -Dexec.args="5000 target benchmarks karatsuba"

echo ""
echo "== VitalsStreaming =="
mvn exec:java -Dexec.mainClass="benchmarks.vitalsstreaming.Main" -Dexec.args="3000 500 target benchmarks vitalsstreaming"

echo ""
echo "== SendPackets =="
mvn exec:java -Dexec.mainClass="benchmarks.sendpackets.Main" -Dexec.args="3000 500 target benchmarks sendpackets"

echo ""
echo "== Mergesort =="
mvn exec:java -Dexec.mainClass="benchmarks.mergesort.Main" -Dexec.args="100 500 target benchmarks mergesort"
