pushd microservices\core\product-service &                call gradlew clean publishToMavenLocal & popd

pushd microservices\support\discovery-server &            call gradlew clean build & popd
pushd microservices\support\edge-server &                 call gradlew clean build & popd
