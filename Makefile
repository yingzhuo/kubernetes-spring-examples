timestamp := $(shell /bin/date "+%F %T")

no_default:
	@echo "no default target"

clean:
	@mvn -f $(CURDIR)/pom.xml clean -q
	@rm -rf $(CURDIR)/_dist || true
	@docker image rm 192.168.99.115/yingzhuo/kse-frontend
	@docker image rm 192.168.99.115/yingzhuo/kse-backend

package:
	@mvn -f $(CURDIR)/pom.xml clean package

build: package
	@docker image build --no-cache -t 192.168.99.115/yingzhuo/kse-frontend $(CURDIR)/kse-frontend/target/docker-context/
	@docker image build --no-cache -t 192.168.99.115/yingzhuo/kse-backend $(CURDIR)/kse-backend/target/docker-context/

release: build
	@docker login --username=${HARBOR_USERNAME} --password=${HARBOR_PASSWORD} 192.168.99.115 &> /dev/null
	@docker image push 192.168.99.115/yingzhuo/kse-frontend
	@docker image push 192.168.99.115/yingzhuo/kse-backend
	@docker logout 192.168.99.115 &> /dev/null
	@mkdir -p $(CURDIR)/_dist/
	@cp $(CURDIR)/kse-frontend/target/docker-context/*.jar $(CURDIR)/_dist/
	@cp $(CURDIR)/kse-backend/target/docker-context/*.jar $(CURDIR)/_dist/

github:
	@git add .
	@git commit -m "$(timestamp)"
	@git push

.PHONY: no_default clean package build release github