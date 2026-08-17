# Spring Retry Self-Invocation Debugging Lab

`@Retryable`を同じSpring bean内から自己呼び出しすると、再試行されず一時的な例外がそのまま返る不具合を再現します。

```bash
mvn test
```

バグコミットは`58a859e`です。修正では`RetryWorker`を別beanに分離し、修正後は同じテストで3回目の試行が成功します。
