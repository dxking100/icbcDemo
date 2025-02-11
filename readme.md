## 工商銀行面試題目
### 技術棧
使用 Maven + JDK 8 進行項目構建
### 測試類
com.test.icbcdemo.IcbcDemoApplicationTests
### 場景流程
在測試類中，每個場景需分別完成以下場景：
1. 開機
2. 初始化產品
3. 過磅(針對需過磅的產品)
4. 加入購物車
5. POS機掃描
6. POS機結賬
7. 打印小票

### 項目包結構

```
com.test.icbcdemo
│
├── core               # 核心代碼類
├── pos                # POS機代碼
├── product            # 產品代碼
└── shoppingcart       # 購物車代碼
```


每個場景中實現相應的邏輯和測試用例，以確保系統的功能完整性和穩定性。