version := 0.0.1-$(shell /bin/date '+%Y%m%d%H%M%S')
backend-image  := 192.168.99.115/yingzhuo/kse-backend:$(version)
backend-image-latest  := 192.168.99.115/yingzhuo/kse-backend:latest
frontend-image := 192.168.99.115/yingzhuo/kse-frontend:$(version)
frontend-image-latest := 192.168.99.115/yingzhuo/kse-frontend:latest

usage:
	@echo "usage          : 显示用法菜单"
	@echo "build-jar      : 构建Jar文件"
	@echo "build-image    : 构建Docker镜像"
	@echo "push-image     : 推送Docker镜像到Harbor"
	@echo "clean          : 清理"
	@echo "version        : 变更版本号"
	@echo "github         : 推送源代码到Github"

build-jar:
	@mvn -f $(CURDIR)/pom.xml clean package -P NonLayeredJar
	@mkdir -p $(CURDIR)/_dist
	@cp $(CURDIR)/kse-backend/target/docker-context/*.jar  $(CURDIR)/_dist
	@cp $(CURDIR)/kse-frontend/target/docker-context/*.jar $(CURDIR)/_dist

build-image:
	@mvn -f $(CURDIR)/pom.xml clean package -P LayeredJar
	@docker image build -t $(frontend-image) $(CURDIR)/kse-frontend/target/docker-context/
	@docker image tag $(frontend-image) $(frontend-image-latest)
	@docker image build -t $(backend-image)  $(CURDIR)/kse-backend/target/docker-context/
	@docker image tag $(backend-image) $(backend-image-latest)

push-image: build-image
	@docker login --username=${HARBOR_USERNAME} --password=${HARBOR_PASSWORD} 192.168.99.115 &> /dev/null
	@docker image push $(frontend-image)
	@docker image push $(frontend-image-latest)
	@docker image push $(backend-image)
	@docker image push $(backend-image-latest)
	@docker logout 192.168.99.115 &> /dev/null

clean:
	@rm -rf $(CURDIR)/_dist/
	@mvn -f $(CURDIR)/pom.xml clean -q
	@docker image ls "192.168.99.115/yingzhuo/kse-*" | xargs docker rmi -f &> /dev/null || true
	@sleep 1
	@docker image rm $(docker image ls --all --filter dangling=true -aq)  &> /dev/null || true

version:
	@mvn -f $(CURDIR)/pom.xml versions:set
	@mvn -f $(CURDIR)/pom.xml -N versions:update-child-modules
	@mvn -f $(CURDIR)/pom.xml versions:commit

github:
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: usage build-jar build-image push-image clean github version
