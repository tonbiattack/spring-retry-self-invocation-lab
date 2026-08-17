# デバッグ記録

バグ状態では`mvn -q test`が`temporary failure: payment-1`で失敗し、`@Retryable`の自己呼び出しが再試行されないことを確認した。`@EnableRetry`は有効で、`charge`自体はproxyを通るが、内部の`attempt`呼び出しは同じインスタンスへの直接呼び出しだった。

`RetryWorker`へ`@Retryable`メソッドを移し、`PaymentService`から別beanを呼ぶ最小修正を行った。修正後は同じテストが成功し、試行回数は3回になった。
