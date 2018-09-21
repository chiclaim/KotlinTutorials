package visibility_modifier

import visibility_modifier.modifier_member.MemberModifier

/**
 * Desc:
 * Created by Chiclaim on 2018/9/21.
 */
class MemberModifierTest {

    val memberModifier: MemberModifier = MemberModifier()


    fun call() {
        memberModifier.username
    }


}