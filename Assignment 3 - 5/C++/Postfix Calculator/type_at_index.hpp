/******************************************************************************
* type_at_index.hpp
*	Template metaprogramming struct used to get the type at the given index
*	within a parameter pack.
*
******************************************************************************/
#pragma once

#include <type_traits>

namespace detail {
	/// @brief Struct used to set the `type` type alias.
	template <typename T>
	struct type_at_index_base
	{
		/// @brief Stores the type at the given index.
		using type = T;
	};

	/// @brief Specialization used when the index exceeds the size
	///		of the parameter pack.
	template <>
	struct type_at_index_base<void>
	{

	};

	/// @brief Definition of the `type_at_index_impl` struct.
	/// @details This version of the struct is never used.
	template <size_t TargetIndex, size_t Index, typename... Ts>
	struct type_at_index_impl
	{

	};

	/// @brief Specialization used when two or more types remain.
	/// @tparam TargetIndex Index to retrieve the type at.
	/// @tparam Index Current index.
	/// @tparam T Current type.
	/// @tparam T1 Next type.
	/// @tparam Ts Remaining types.
	template <size_t TargetIndex, size_t Index, typename T,
		typename T1, typename... Ts>
	struct type_at_index_impl<TargetIndex, Index,
		T, T1, Ts...> : public std::conditional_t<
			TargetIndex == Index,
			type_at_index_base<T>,
			type_at_index_impl<TargetIndex, Index + 1, T1, Ts...>>
	{

	};

	/// @brief Specialization used when only one type remains.
	/// @tparam TargetIndex Index to retrieve the type at.
	/// @tparam Index Current index.
	/// @tparam T Current type.
	template <size_t TargetIndex, size_t Index, typename T>
	struct type_at_index_impl<TargetIndex, Index, T> :
		public std::conditional_t<
			TargetIndex == Index,
			type_at_index_base<T>,
			type_at_index_base<void>>
	{

	};
}

template <size_t Index, typename... Ts>
struct type_at_index : public detail::type_at_index_impl<
	Index, 0, Ts...>
{
	static_assert(Index < sizeof...(Ts), "Error: The given index "
		"is not a valid index within the parameter pack.");
};

/// @brief Convenience type alias.
template <size_t Index, typename... Ts>
using type_at_index_t = typename type_at_index<Index, Ts...>::type;