echo ""
echo "== amend HelloRoles =="
choral amend -s src/main/choral/amend/hello-roles -l src/main/choral/amend/hello-roles -t target/amend HelloRoles

echo ""
echo "== amend SimpleArithmetic =="
choral amend -s src/main/choral/amend/simple-arithmetic -l src/main/choral/amend/simple-arithmetic -t target/amend SimpleArithmetic

echo ""
echo "== amend Increments =="
choral amend -s src/main/choral/amend/increments -l src/main/choral/amend/increments -t target/amend Increments
