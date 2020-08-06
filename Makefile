timestamp := $(shell /bin/date "+%F %T")
version := 0.0.1

no_default:
	@echo "no default target"

clean:
	@mvn -f $(CURDIR)/pom.xml clean -q
	@docker image rm 192.168.99.115/yingzhuo/kse-frontend &> /dev/null || true
	@docker image rm 192.168.99.115/yingzhuo/kse-backend &> /dev/null || true

package:
	@mvn -f $(CURDIR)/pom.xml clean package

build: package
	@docker image build -t 192.168.99.115/yingzhuo/kse-frontend --build-arg VERSION=$(version)  $(CURDIR)/kse-frontend/target/docker-context/
	@docker image build -t 192.168.99.115/yingzhuo/kse-backend  --build-arg VERSION=$(version) $(CURDIR)/kse-backend/target/docker-context/

release: build
	@docker login --username=${HARBOR_USERNAME} --password=${HARBOR_PASSWORD} 192.168.99.115 &> /dev/null
	@docker image push 192.168.99.115/yingzhuo/kse-frontend
	@docker image push 192.168.99.115/yingzhuo/kse-backend
	@docker logout 192.168.99.115 &> /dev/null

github:
	@git add .
	@git commit -m "$(timestamp)"
	@git push

.PHONY: no_default clean package build release github