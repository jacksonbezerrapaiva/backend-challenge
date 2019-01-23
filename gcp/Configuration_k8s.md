#Configurar o GCP

1 - Instalar sdk do google.
2 - gcloud init
3 - configurar a conta do gmail

4 - install kubernetes
5 - install minikube

6 - gcloud container clusters create CLUSTER_NAME --zone COMPUTE_ZONE

7 - Criar configuração do proxy mysql

gcloud sql users create proxyuser cloudsqlproxy~% --instance=invillia --password=123mudar
kubectl create secret generic cloudsql-instance-credentials --from-file=credentials.json=configs/credencial.json
kubectl create secret generic cloudsql-db-credentials --from-literal=username=proxyuser --from-literal=password=123mudar

7 -  kubectl apply -f gcp/mysql.yml
8 -  kubectl apply -f gcp/service.yml
9 - Próximo passo é criar o acionador do CloudBuild no GCP
Nessa configuração você vai dizer qual o ramo que pretende fazer o deploy automático.
10 - Próximo passo e conforme foi adicionado algo no branch seja realizada a nova release.


