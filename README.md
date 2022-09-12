# keycloak-integration
Integration keycloak with spring boot security
Hướng dẫn tích hợp spring boot security và keycloak. Cả `Authentication` và `Authorization` đều thực hiện tại keycloak server.

## Triển khai và cấu hình Keycloak
### Triển khai
- Run file `docker-compose.yaml`

### Cấu hình
Truy cập vào link `Administration Console` [http://localhost:2020](http://localhost:2020)

1. Tạo realm mới [ref](https://www.keycloak.org/docs/13.0/getting_started/#creating-a-realm-and-a-user)
   - Di chuột vào realm `master` > click `Create Realm`
   - Điền thông tin realm name `itsol` > click `Create`
   - Sau khi tạo thành công chuyển workspace về `itsol` reaml để thực hiện các bước tiếp theo

2. Tạo user
   - Click `Users` tại menu bên trái
   - Clieck `Add user`
   - Nhập username (vd: `dongnv`)
   - Click `Save`
3. Tạo mới role
   - Click `Roles` tại menu bên trái
   - Click `Add role`
   - Nhập role name (vd: `group-manager`) </br>
   :::note Không tạo liên kết role đến bất kỳ `user` nào:::
4. Tạo `Client`
  - Click menu `Client`
  - Cliek `Create client`
  - Nhập 
    - `Client type` = OpenID Connect
    - `Client ID` = login-app
  - Click `Next` để sang bước tiếp theo
  - Kiểm tra các trường
    - `Client authentication` > enable
    - `Authorization` > enable
  Click `Save`
5. Tạo các `Scope`
   - Truy cập vào `login-app` client
   - Tại `Authorization` > `Scopes` tạo 4 scope
   ```
   group.create, group.update, group.delete, group.view
   ```
6. Tạo Resource
   - Tại `Authorization` > `Resources` click `Create resource`
   - Nhập `name` (vd: `[RS] Phòng ban`)
   - Trường `Authorization scopes` chọn tất cả 4 scope vừa tạo phía trên
   - Click `Save`

7. Tạo policy
   - Tại `Authorization` > `Policies` click `Create policy`
   - Chọn `policy type` = `Role`
   - Điền thông tin `Name` (vd: `[PO] Quản lý phòng ban`)
   - Click `Add roles` > chọn role `group-manager`
   - Click `Save`

8. Tạo Permission
   - Tại `Authorization` > `Permissions` click `Create permission` type `Create scope-base permission`
   - Điền tên permission
   - Chọn `Resources` > `[RS] Phòng ban`
   - Trường `Authorization scopes` chọn tất cả 4 scope vừa tạo phía trên
   - Chọn `[PO] Quản lý phòng ban`
   - Click `Save`

