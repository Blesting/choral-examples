echo ""
echo "== amend channelsAsArgs =="
choral amend -s src/main/choral/amend/channels-as-args -l src/main/choral/ -t target/amend channelsAsArgs

echo ""
echo "== amend channelsAsFields =="
choral amend -s src/main/choral/amend/channels-as-fields -l src/main/choral/ -t target/amend channelsAsFields

echo ""
echo "== amend BuyerSellerShipper =="
choral amend -s src/main/choral/amend/buyer-seller-shipper -l src/main/choral/ -t target/amend BuyerSellerShipper

echo ""
echo "== amend SimpleMethodCalls =="
choral amend -s src/main/choral/amend/simple-method-calls -l src/main/choral/amend/simple-method-calls -t target/amend SimpleMethodCalls

echo ""
echo "== amend SimpleIfStatements =="
choral amend -s src/main/choral/amend/simple-if-statements -l src/main/choral/amend/simple-if-statements -t target/amend SimpleIfStatements

echo ""
echo "== amend Increments =="
choral amend -s src/main/choral/amend/increments -l src/main/choral/amend/increments -t target/amend Increments

echo ""
echo "== amend SimpleArithmetic =="
choral amend -s src/main/choral/amend/simple-arithmetic -l src/main/choral/amend/simple-arithmetic -t target/amend SimpleArithmetic

echo ""
echo "== amend HelloRoles =="
choral amend -s src/main/choral/amend/hello-roles -l src/main/choral/amend/hello-roles -t target/amend HelloRoles

