echo ""
echo "== amend DownloadFile =="
choral amend -s src/main/choral/amend/ -l src/main/choral/ -t target/generated-sources/choral/ --epp DownloadFile

echo ""
echo "== amend DistAuth10 =="
choral amend -s src/main/choral/amend/ -l src/main/choral/ -t target/generated-sources/choral/ --epp DistAuth10

echo ""
echo "== amend DiffieHellman =="
choral amend -s src/main/choral/amend/ -l src/main/choral/ -t target/generated-sources/choral/ --epp DiffieHellman

echo ""
echo "== amend VitalsStreaming =="
choral amend -s src/main/choral/amend/vitals-streaming -l src/main/choral/ -t target/generated-sources/choral/ --epp VitalsStreaming

echo ""
echo "== amend Karatsuba =="
choral amend -s src/main/choral/amend/karatsuba -l src/main/choral/ -t target/generated-sources/choral/ --epp Karatsuba

echo ""
echo "== amend SplitAndCombine =="
choral amend -s src/main/choral/amend/split-and-combine -l src/main/choral/ -t target/generated-sources/choral/ --epp SplitAndCombine

echo ""
echo "== amend SSOWithRetry =="
choral amend -s src/main/choral/amend/sso-with-retry -l src/main/choral/ -t target/generated-sources/choral/ --epp SSOWithRetry

echo ""
echo "== amend Quicksort =="
choral amend -s src/main/choral/amend/quicksort -l src/main/choral/ -t target/generated-sources/choral/ --epp Quicksort

echo ""
echo "== amend Mergesort =="
choral amend -s src/main/choral/amend/mergesort -l src/main/choral/ -t target/generated-sources/choral/ --epp Mergesort

echo ""
echo "== amend SendPackets =="
choral amend -s src/main/choral/amend/sendpackets -l src/main/choral/ -t target/generated-sources/choral/ --epp SendPackets

echo ""
echo "== amend ConsumeItems =="
choral amend -s src/main/choral/amend/consume-items -l src/main/choral/ -t target/generated-sources/choral/ --epp ConsumeItems
