timestamp := $(shell /bin/date "+%F %T")
namespace := "kse"

usage:
	@echo "usage           -> 显示本菜单"
	@echo "reboot-backend  -> 重启backend节点"
	@echo "reboot-frontend -> 重启frontend节点"
	@echo "reboot-all      -> 重启所有节点"
	@echo "init            -> 初始化"
	@echo "stop            -> 停止所有"
	@echo "status          -> 查看所有POD / SVC的状态"

reboot-backend:
	@kubectl --namespace=$(namespace) delete deployments.apps backend 2> /dev/null || true
	@kubectl apply -f $(CURDIR)/02-kse-backend.yaml

reboot-frontend:
	@kubectl --namespace=$(namespace) delete deployments.apps frontend 2> /dev/null || true
	@kubectl apply -f $(CURDIR)/03-kse-frontend.yaml

reboot-all: reboot-backend reboot-frontend

init:
	@kubectl apply -f $(CURDIR)/01-kse-init.yaml
	@kubectl apply -f $(CURDIR)/04-kse-ingress.yaml

stop:
	@kubectl delete namespaces $(namespace)

status:
	@echo ""
	@kubectl --namespace=$(namespace) get pod -o wide
	@echo ""
	@echo "-------------------------------------------------------------------------------------------------------------------"
	@kubectl --namespace=$(namespace) get svc -o wide
	@echo ""

.PHONY: usage reboot-backend reboot-frontend init stop status