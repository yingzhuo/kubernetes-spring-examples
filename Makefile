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
	@docker image build -t 192.168.99.115/yingzhuo/kse-frontend $(CURDIR)/kse-frontend/target/docker-context/
	@docker image build -t 192.168.99.115/yingzhuo/kse-backend  $(CURDIR)/kse-backend/target/docker-context/

push-image: build-image
	@docker login --username=${HARBOR_USERNAME} --password=${HARBOR_PASSWORD} 192.168.99.115 &> /dev/null
	@docker image push 192.168.99.115/yingzhuo/kse-frontend
	@docker image push 192.168.99.115/yingzhuo/kse-backend
	@docker logout 192.168.99.115 &> /dev/null

clean:
	@rm -rf $(CURDIR)/_dist/
	@mvn -f $(CURDIR)/pom.xml clean -q
	@docker image rm 192.168.99.115/yingzhuo/kse-frontend &> /dev/null || true
	@docker image rm 192.168.99.115/yingzhuo/kse-backend &> /dev/null || true
	@docker image rm $(docker image ls --all --filter dangling=true -q)  &> /dev/null || true

version:
	@mvn -f $(CURDIR)/pom.xml versions:set
	@mvn -f $(CURDIR)/pom.xml -N versions:update-child-modules
	@mvn -f $(CURDIR)/pom.xml versions:commit

github:
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: usage build-jar build-image push-image clean github version
