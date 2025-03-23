echo ""
echo "== amend Quicksort =="
choral epp -s src/main/choral/modified/quicksort -l src/main/choral/ -t target/generated-sources/choral/ Quicksort

echo ""
echo "== amend Mergesort =="
choral epp -s src/main/choral/modified/mergesort -l src/main/choral/ -t target/generated-sources/choral/ Mergesort

echo ""
echo "== amend Karatsuba =="
choral epp -s src/main/choral/modified/ -l src/main/choral/ -t target/generated-sources/choral/ Karatsuba

