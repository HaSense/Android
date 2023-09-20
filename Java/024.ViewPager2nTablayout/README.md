
# 멀티탭 화면구현
## ViewPager2와 TabLayout를 활용한 화면만들기


### ViewPager와 ViewPager2 비교

| 특 성   | ViewPager                                   | ViewPager2                                                                 |
|---------------------|---------------------------------------------|----------------------------------------------------------------------------|
| **기본 구조**           | `PagerAdapter` 사용                          | `RecyclerView.Adapter` 사용 (RecyclerView의 모든 기능 상속)                 |
| **방향 지원**          | 주로 수평 스크롤 지원                          | 수평 및 수직 스크롤 지원                                                      |
| **RTL 지원**           | 제한적                                       | 완벽하게 지원                                                                |
| **페이지 변환**         | `PageTransformer` 사용                        | 여러 `PageTransformer` 동시 사용 가능                                           |
| **DiffUtil 지원**       | 직접 구현 필요                                | 자연스럽게 지원 (RecyclerView.Adapter 기반)                                      |
| **항목 갱신**          | `notifyDataSetChanged()` 사용 (비효율적 가능성) | 효율적인 갱신 (RecyclerView.Adapter 메커니즘 사용)                               |
| **애니메이션**          | 제한적                                       | 다양한 애니메이션 (`RecyclerView.ItemAnimator` 사용)                             |
| **프래그먼트 지원**      | `FragmentPagerAdapter` 및 `FragmentStatePagerAdapter` 사용 | `FragmentStateAdapter` 사용                                                    |
| **스크롤 동작**         | 완전히 제어 불가                               | `setUserInputEnabled()`로 사용자 스크롤 동작 활성화/비활성화 가능                |


