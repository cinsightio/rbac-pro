
#What is RBACPro
This open source project is a simple, complete and productin ready role based access control service.
#Why

The goal is to enable enterprize, large or small, to adopt solid access control
service from day one. Access Control has moved to the top 1 threats
of OWASP 10 2021 (https://owasp.org/www-project-top-ten/).  
At the core, it is based on the classic RBAC96 model (https://www.profsandhu.com/infs767/infs767fall03/lecture01-2.pdf) as well as the NIST standard (https://csrc.nist.gov/Projects/Role-Based-Access-Control). 
They are augmented with modern factors for access control to support rich context and meet the requirements of fine-grained access control. Thus, we call this RBACPro.

#Features:
* Token/Session management service to promote temporal credentials.
* Multi-tenant user-role, role-permission management  
* Persistent in relational database. 
* Logging with flexible plugins such as Elastic search, S3, etc, 
* Integration with Vault, AWS secret management to make it easier to integrate with your services run anywhere. 
* Global scalable authorization check with JWT token, generated tokens.

Stay tuned for the first version and all comments/collaborations are welcome.

#How to use
