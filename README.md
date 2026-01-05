# BurgerKing Clone Coding

## 📑 목차
1. 팀 소개

2. 프로젝트 개요

3. 개발 규칙

4. 개발환경 및 협업 도구

5. 요구사항정의서

6. 스토리보드

7. 테스트케이스

8. 회고

---

## 1. 팀소개 👥

<table align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/a4a4df1d-217c-4a78-b913-fe9620239bad"
           width="120" height="120" style="object-fit: cover;"/><br>
      <strong>김채우</strong><br>
      chaewookim
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/3b2d6ed3-7c1a-4441-98d4-d7e88175705b"
           width="120" height="120" style="object-fit: cover;"/><br>
      <strong>김윤경</strong><br>
      yk5096
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/8c15d0e9-8d84-4211-b4bb-732c6055b872"
           width="120" height="120" style="object-fit: cover;"/><br>
      <strong>이경민</strong><br>
      LKM1027
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/eac2eb2c-a595-4803-821f-f06f1944853f"
           width="120" height="120" style="object-fit: cover;"/><br>
      <strong>이형욱</strong><br>
      danielhw99
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/06b960c5-0b08-47b3-801f-7591a17aa111"
           width="120" height="120" style="object-fit: cover;"/><br>
      <strong>조윤호</strong><br>
      cho-yunho01
    </td>
  </tr>
</table>




---

## 2. 프로젝트 개요 📌

본 프로젝트는 한국 버거킹 공식 홈페이지를 대상으로 한 클론코딩 프로젝트입니다.  
웹 개발 학습 과정에서 실제 서비스 수준의 웹사이트를 분석하고 구현해보는 것이 가장 효과적이라고 판단하여, 다양한 기능과 구조를 갖춘 한국 버거킹 홈페이지를 선정하게 되었습니다.

한국 버거킹 홈페이지는 프론트엔드 중심의 프로젝트를 진행하기에 적합한 구조를 가지고 있으며, 동시에 메뉴 데이터 관리와 같은 기본적인 백엔드 요소도 함께 포함하고 있습니다.  

단순한 정적 페이지가 아닌 실제 서비스 형태의 화면 구성과 데이터 흐름을 갖추고 있어, 프론트엔드 구현을 중심으로 하면서도 백엔드와의 연동 과정을 함께 경험할 수 있다고 판단하였습니다.

이러한 이유로 프론트엔드 프로젝트로서 적절한 난이도와 범위를 가지고 있는 한국 버거킹 홈페이지를 클론코딩 대상으로 선정하게 되었습니다.

- 대상 사이트: 한국 버거킹 공식 홈페이지  
- URL: https://www.burgerking.co.kr/


---


## 3. 개발규칙 🧰

### ⭐ Code Convention

---

<details>
<summary style = " font-size:1.3em;">Naming</summary>
<div markdown="1">

- 패키지 : 언더스코어(`_`)나 대문자를 섞지 않고 소문자를 사용하여 작성합니다.
- 클래스 : 클래스 이름은 명사나 명사절로 지으며, 대문자 카멜표기법(Upper camel case)을 사용합니다.
- 메서드 : 메서드 이름은 동사/전치사로 시작하며, 소문자 카멜표기법(Lower camel case)를 사용합니다. 의도가 전달되도록 최대한 간결하게 표현합니다.
- 변수 : 소문자 카멜표기법(Lower camel case)를 사용합니다.
- ENUM, 상수 : 상태를 가지지 않는 자료형이면서 `static final`로 선언되어 있는 필드일 때를 상수로 간주하며, 대문자와 언더스코어(Upper_snake_case)로 구성합니다.
- DB 테이블: 소문자와 언더스코어로(lower_snake_case) 구성합니다.
- 컬렉션(Collection): **복수형**을 사용하거나 **컬렉션을 명시합니다**. (Ex. userList, users, userMap)
- LocalDateTime: 접미사에 **Date**를 붙입니다.


</div>
</details>
<details>
<summary style = " font-size:1.3em;">Comment</summary>
<div markdown="1">

### 1. 한줄 주석은 // 를 사용한다.

```java
// 하이~
```

### 2. Bracket 사용 시 내부에 주석을 작성한다.

```java
/*
   하이~!
*/
```

### 3. 주요 함수에 대한 주석

```java
/*
 * 입력 : 인덱스:Long
 * 기능 : 유저 인덱스로 db에 접근해 유저 객체를 반환한다
 * 출력 : 유저:User
 */
public User getUser(Long idx)
```

</div>
</details>
<details>
<summary style = " font-size:1.3em;">Import</summary>
<div markdown="1">

### 1. 소스파일당 1개의 탑레벨 클래스를 담기

> 탑레벨 클래스(Top level class)는 소스 파일에 1개만 존재해야 한다. ( 탑레벨 클래스 선언의 컴파일타임 에러 체크에 대해서는 [Java Language Specification 7.6](http://docs.oracle.com/javase/specs/jls/se7/html/jls-7.html#jls-7.6) 참조 )

### 2. static import에만 와일드 카드 허용

> 클래스를 import할때는 와일드카드(`*`) 없이 모든 클래스명을 다 쓴다. static import에서는 와일드카드를 허용한다.

### 3. 애너테이션 선언 후 새줄 사용

> 클래스, 인터페이스, 메서드, 생성자에 붙는 애너테이션은 선언 후 새줄을 사용한다. 이 위치에서도 파라미터가 없는 애너테이션 1개는 같은 줄에 선언할 수 있다.


### 4. 배열에서 대괄호는 타입 뒤에 선언

> 배열 선언에 오는 대괄호(`[]`)는 타입의 바로 뒤에 붙인다. 변수명 뒤에 붙이지 않는다.

### 5. `long`형 값의 마지막에 `L`붙이기

> long형의 숫자에는 마지막에 대문자 'L’을 붙인다. 소문자 'l’보다 숫자 '1’과의 차이가 커서 가독성이 높아진다.

</div>
</details>
<details>
<summary style = " font-size:1.3em;">URL</summary>
<div markdown="1">

### URL

URL은 RESTful API 설계 가이드에 따라 작성합니다.

- HTTP Method로 구분할 수 있는 get, put 등의 행위는 url에 표현하지 않습니다.
- 마지막에 `/` 를 포함하지 않습니다.
- `_` 대신 `-`를 사용합니다.
- 소문자를 사용합니다.
- 확장자는 포함하지 않습니다.


</div>
</details>

<br>

### ☀️ Commit Convention

---

<details>
<summary style = " font-size:1.3em;">Rules</summary>
<div markdown="1">

### 1. Git Flow

작업 시작 시 선행되어야 할 작업은 다음과 같습니다.


> 1. issue를 생성합니다. 이 때 issue에 대한 milestone, label, project를 설정합니다.
> 2. feature branch를 생성합니다.
> 3. add → commit → push → pull request 를 진행합니다.
> 4. pull request를 develop branch로 squash merge 합니다.
> 5. 이전에 merge된 작업이 있을 경우 다른 branch에서 진행하던 작업에 merge된 작업을 pull 받아옵니다.
> 6. 종료된 issue와 pull request의 label을 관리합니다.


### 2. Etc

준수해야 할 규칙은 다음과 같습니다.

> 1. develop branch에서의 작업은 원칙적으로 금지합니다. 단, README 작성은 develop branch에서 수행합니다.
> 2. commit, push, merge, pull request 등 모든 작업은 오류 없이 정상적으로 실행되는 지 확인 후 수행합니다.

</div>
</details>

<details>
<summary style = " font-size:1.3em;">Branch</summary>
<div markdown="1">

### 1. Branch

branch는 작업 단위 & 기능 단위로 생성하며 이는 issue를 기반으로 합니다.

### 2. Branch Naming Rule

branch를 생성하기 전 issue를 먼저 작성합니다. issue 작성 후 생성되는 번호와 domain 명을 조합하여 branch의 이름을 결정합니다. `<Prefix>/<Issue_Number>-<Domain>` 의 양식을 준수합니다.

### 3. Prefix

- `main` : 개발이 완료된 산출물이 저장될 공간입니다.
- `develop`: feature branch에서 구현된 기능들이 merge될 default branch 입니다.
- `feature`: 기능을 개발하는 branch 입니다. 이슈 별 & 작업 별로 branch를 생성 후 기능을 개발하며 naming은 소문자를 사용합니다.


### 4. Etc

- `feature/7-user`, `feature/5-config`


</div>
</details>

<details>
<summary style = " font-size:1.3em;">Issue</summary>
<div markdown="1">

### 1. Issue

작업 시작 전 issue 생성이 선행되어야 합니다. issue 는 작업 단위 & 기능 단위로 생성하며 생성 후 표시되는 issue number 를 참조하여 branch 이름과 commit message를 작성합니다.

issue 제목에는 기능의 대표적인 설명을 적고 내용에는 세부적인 내용 및 작업 진행 상황을 작성합니다.

issue 생성 시 github 오른편의 assignee, label, milestone, project를 적용합니다. assignee는 해당 issue 담당자, label은 작업 내용을 추가합니다.

### 2. Issue Naming Rule

`[<Prefix>] <Description>` 의 양식을 준수하되, prefix는 commit message convention을 따릅니다.

### 3. Etc

<aside>
[feat] 약속 잡기 API 구현
<br/>[chore] spring data JPA 의존성 추가

</aside>

---

</div>
</details>

<details>
<summary style = " font-size:1.3em;">Commit</summary>
<div markdown="1">

### 1. Commit Message Convention

`[<Prefix>] #<Issue_Number> <Description>` 의 양식을 준수합니다.

- **feat** : 새로운 기능 구현 `[feat] #11 구글 로그인 API 기능 구현`
- **fix** : 코드 오류 수정 `[fix] #10 회원가입 비즈니스 로직 오류 수정`
- **del** : 쓸모없는 코드 삭제 `[del] #12 불필요한 import 제거`
- **docs** : README나 wiki 등의 문서 개정 `[docs] #14 리드미 수정`
- **refactor** : 내부 로직은 변경 하지 않고 기존의 코드를 개선하는 리팩터링 `[refactor] #15 코드 로직 개선`
- **chore** : 의존성 추가, yml 추가와 수정, 패키지 구조 변경, 파일 이동 `[chore] #21 yml 수정`, `[chore] #22 lombok 의존성 추가`
- **test**: 테스트 코드 작성, 수정 `[test] #20 로그인 API 테스트 코드 작성`
- **style** : 코드에 관련 없는 주석 달기, 줄바꿈

</div>
</details>

<details>
<summary style = " font-size:1.3em;">Pull Request</summary>
<div markdown="1">

### 1. Pull Request

develop & main branch로 merge할 때에는 pull request가 필요합니다. pull request의 내용에는 변경된 사항에 대한 설명을 명시합니다.

### 2. Pull Request Naming Rule

`[<Prefix>] #<Issue_Number> <Description>` 의 양식을 준수하되, prefix는 commit message convention을 따릅니다.

### 3. Etc

[feat] #3 약속 잡기 API 구현
<br/>[chore] #5 spring data JPA 의존성 추가

</div>
</details>

---

## 4. 협업도구

### 🛠 개발 환경 및 기술 스택

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black)
![Vue.js](https://img.shields.io/badge/Vue.js-4FC08D?style=flat-square&logo=vuedotjs&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white)

### 💻 IDE & Tools

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000?style=flat-square&logo=intellijidea&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white)

### 🤝 협업 도구

![Discord](https://img.shields.io/badge/Discord-5865F2?style=flat-square&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)
![Google Drive](https://img.shields.io/badge/GoogleDrive-4285F4?style=flat-square&logo=googledrive&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=figma&logoColor=white)

---

## 5. 요구사항정의서

<img width="1086" height="797" alt="Image" src="https://github.com/user-attachments/assets/ed6eee56-6085-4468-b7fe-ee7264e70b76" />

<img width="1086" height="535" alt="Image" src="https://github.com/user-attachments/assets/ef96dac7-2650-429d-9544-3d0fc9c58e8d" />

<img width="1086" height="715" alt="Image" src="https://github.com/user-attachments/assets/2835ff67-e348-44c2-b691-392f313f75d3" />

<img width="1086" height="545" alt="Image" src="https://github.com/user-attachments/assets/83c8ab43-0259-4910-acb5-5c71591b1f42" />

<img width="1086" height="448" alt="Image" src="https://github.com/user-attachments/assets/ba9c77fb-14ef-42b5-b955-12d75f21a94e" />

---

## 6. 스토리보드 🗺️
### 메인페이지
<img width="725" height="612" alt="Image" src="https://github.com/user-attachments/assets/3a93d855-580c-49ba-99c8-544509e1371d" />

### Menu
<img width="781" height="602" alt="Image" src="https://github.com/user-attachments/assets/49367bff-d715-45b8-a895-2dd6fd8908f4" />

### Story
<img width="807" height="622" alt="Image" src="https://github.com/user-attachments/assets/f47fd93d-bcde-4119-944b-ba69788e81ad" />

### News
<img width="990" height="763" alt="Image" src="https://github.com/user-attachments/assets/926d61b2-5aa1-4910-9c46-bb103f087e09" />

### Store
<img width="1248" height="731" alt="Image" src="https://github.com/user-attachments/assets/c4ba42ab-5aee-4c87-8442-3c2f07a8c796" />

### Customer
<img width="723" height="341" alt="Image" src="https://github.com/user-attachments/assets/52f85c96-efea-414c-b5a6-0d6904a73a26" />

## 7. 테스트케이스 🧪
<img width="1663" height="667" alt="Image" src="https://github.com/user-attachments/assets/340b0b88-e04b-4657-ade5-a4980e8494e1" />

<img width="1666" height="362" alt="Image" src="https://github.com/user-attachments/assets/cb023181-fa08-4ab2-bf19-e07f0e6edb46" />

<img width="1665" height="436" alt="Image" src="https://github.com/user-attachments/assets/f98f395f-c147-4db9-bf06-e583bc472fda" />

## 8. 회고 ✍️

| 이름 | 회고 |
| --- | --- |
| 김채우 | 프론트와 백엔드의 연결에 집중하기 위해 클론 코딩을 선택해 백엔드 구현의 부담을 최대한 덜고자 했다. 하지만 버거킹 홈페이지에는 기능이 많지 않았기 때문에 minIO를 잘 사용해 보는 것이 두번째 목표였다. 버켓을 만들고 이미지를 업로드, 수정, 삭제하는 과정이 생각보다 오류가 많이 발생했고 익숙하지는 않았지만 그래도 마지막에 성공적으로 구현할 수 있었다. MariaDB와 minIO를 둘 다 사용하기 위해 데이터베이스의 구조를 짜는 것이 재미있었다. 백엔드로 구현한 것을 프론트에서 보여주는 것이 바로 변화점이 보여 재밌었다. |
| 김윤경 | 한 두번의 프로젝트 경험이 쌓이다보니, 이 전 프로젝트에서 구현한 기능은 조금 수월하게 구현할 수 있었다. 아직도 처음 써보는 툴의 연속이지만 더 익숙해지고 적응해가려고 노력했다. 코드를 작성하다가 궁금하거나 막히는 점이 있으면 그 전에는 오래 고민했다면, 지금은 그 비중이 조금 줄어들고 상대방의 의견을 더 자주물어보고 어떻게 참고하면 좋을지 도움을 요청했다. 팀원이 구현한 기능을 내가 이용할때는 의도가 무엇인지, 어떻게 기능을 적용하면 좋을지 한 번 더 생각해보는 경험을 했다. |
| 이경민 | 이번 프론트엔드 단위 프로젝트는 클론 코딩 프로젝트였지만, 백엔드부터 프론트엔드까지 전 과정을 직접 구현했다는 점에서 큰 성취감을 느꼈다. 기능 단위로 커밋과 PR을 촘촘하게 관리하며 작업하면서 버전 관리에 대한 이해도가 높아졌고, GitHub 활용에도 자연스럽게 익숙해질 수 있었다. 클론 코딩이라는 특성 덕분에 오히려 실제 서비스 수준의 프론트엔드 디자인과 UI 구조를 깊이 있게 학습할 수 있었다. 초기에는 padding과 margin 개념이 헷갈려 의도와 다른 여백이 발생하기도 했지만, CSS 레이아웃과 JavaScript 동작 원리를 익히면서 점차 안정적으로 구현할 수 있게 되었다. 최종적으로는 버거킹 실제 웹사이트에 존재하는 주요 UI 요소와 기능들을 모두 구현할 수 있었고, 프론트엔드 전반에 대한 이해도를 한 단계 끌어올릴 수 있었던 프로젝트였다. |
| 이형욱 | 버거킹 웹사이트 클론 코딩 프로젝트는 화면을 따라 만드는 수준을 넘어 프론트엔드와 백엔드가 실제로 어떻게 역할을 분리하고 데이터 흐름을 구성하는지 체감할 수 있었던 작업이었다. Vue/Vite 환경에서 라우팅과 컴포넌트 분리를 통해 메인·고객지원 같은 도메인을 구조화하고, FAQ 리스트–상세 흐름을 구현하며 조건부 렌더링과 화면 전환 설계의 중요성을 배웠다. 백엔드에서는 Controller–Service–Repository 계층을 도메인별로 분리하고 DTO로 응답을 정리하는 과정에서 REST API 설계와 응답 일관성이 프론트 접합에 얼마나 큰 영향을 주는지 확인했다. 특히 MinIO와 파일 메타데이터를 활용해 이미지 URL을 관리하면서 파일 처리도 단순 업로드가 아니라 운영 가능한 구조로 설계해야 한다는 관점을 얻은 프로젝트였습니다. |
| 조윤호 | 백엔드 작업은 이전 프로젝트에서 경험이 있었기 때문에 전반적인 구조가 비교적 익숙했고, 그 덕분에 수월하게 진행할 수 있었다. 특히 이번 프로젝트에서는 모르는 부분을 AI에 전적으로 의존하기보다는, 필요한 최소한의 도움만 참고하여 스스로 코드를 작성하려고 노력했다는 점에서 개인적으로 만족스러운 경험이었다. 반면 프론트엔드 구현은 예상보다 많은 시간이 소요되었다. 실제 서비스 화면을 최대한 동일하게 구현하려다 보니 세부적인 UI와 구조에 많은 공을 들이게 되었고, 그 과정에서 시행착오도 많았다. 다만 이러한 과정을 통해 프론트엔드 화면 구성과 구조가 머릿속에 자연스럽게 정리되었고, 결과적으로는 의미 있는 경험이 되었다고 느낀다. |

