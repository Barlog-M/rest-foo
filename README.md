Example Spring REST app for some experiments

`/foo` — sync

`/bar` — async

### [Vegeta](https://github.com/tsenart/vegeta)
```
echo "GET https://gentle-headland-18509.herokuapp.com/foo\nAccept: application/json;charset=UTF-8\nContent-Type: application/json;charset=UTF-8" | vegeta -cpus 4 attack -duration=1m | vegeta report
```
